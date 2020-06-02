
package com.security.core.validate.code.impl;

import org.springframework.security.core.AuthenticationException;

/**
 * @author zhailiang
 * AuthenticationException 所有身份认证过程中异常基类
 */
public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
