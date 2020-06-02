package com.security.security.browser;

import com.security.core.properties.SecurityProperties;
import com.security.core.validate.code.impl.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.GET, "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.gif", "/**/*.jpg");
//    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
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
