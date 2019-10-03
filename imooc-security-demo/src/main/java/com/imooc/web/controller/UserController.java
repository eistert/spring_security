/**
 *
 */
package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.imooc.exception.UserNotExistException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;


/**
 * @author ai
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

//	@Autowired
//	private ProviderSignInUtils providerSignInUtils;

//	@Autowired
//	private AppSingUpUtils appSingUpUtils;

//	@Autowired
//	private SecurityProperties securityProperties;

//	@PostMapping("/regist")
//	public void regist(User user, HttpServletRequest request) {
//
//		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
//		String userId = user.getUsername();
//		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
//	}

//	@GetMapping("/me")
//	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {

//		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
//		
//		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
//					.parseClaimsJws(token).getBody();
//		
//		String company = (String) claims.get("company");
//		
//		System.out.println(company);

//		return user;
//	}

    /**
     * 创建用户
     * @param user
     * @return
     */
    @PostMapping
//	@ApiOperation(value = "创建用户")
    public User create(@Valid @RequestBody User user) {

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    /**
     * 更新用户用put请求
     * @param user
     * @param errors
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }

    /**
     * PageableDefault 给分页参数执行默认值
     *
     * @param condition
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
//	@ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

        // 反射的toString 工具把参数打印到控制台上
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     *拿取单个用户的信息
     * JsonView 区分接口返回数据
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {
//        throw new UserNotExistException(id);
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @RequestMapping("/me")
    public Object getCurrentUser(Authentication authentication) {

        return authentication.getPrincipal();
//        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
