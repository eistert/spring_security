/**
 *
 */
package com.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *验证码生成逻辑可配置
 */
public interface ValidateCodeGenerator {

    ImageCode generate(ServletWebRequest request);

}
