package com.security.security.browser.config;

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * 浏览器环境下安全配置主类
 *
 * @author zhailiang
 * WebSecurityConfigurerAdapter web 安全配置适配器
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler imoocAuthenctiationFailureHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        // 调用初始化方法
        validateCodeFilter.afterPropertiesSet();


        // 表单登录
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//        http.httpBasic()
                // 自定义登录页面
                .loginPage("/authentication/require")
                // 提交请求配置
                .loginProcessingUrl("/authentication/form")
                // 登录成功之后要调用的处理器
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenctiationFailureHandler)
                .and()
                // rememberMe
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                // 对请求授权
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getSignInPage(),
                        "/code/image"
                ).permitAll()
                // 所有的请求
                .anyRequest()
                // 都要认证授权
                .authenticated()
                .and().csrf().disable();

    }


}
