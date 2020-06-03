/**
 *
 */
package com.security.core.validate.code.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {

    /**
     * 短信验证码发送接口
     * @param mobile
     * @param code
     */
    void send(String mobile, String code);

}
