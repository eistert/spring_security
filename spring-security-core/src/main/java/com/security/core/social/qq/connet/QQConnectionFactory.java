package com.security.core.social.qq.connet;


import com.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author zhailiang
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * @param providerId 服务商的唯一标识
     * @param appId
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
    	//
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }

}
