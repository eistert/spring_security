package com.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成逻辑可配置，封装了验证码的生成逻辑
 * 设计思想：分成封装
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);

}
