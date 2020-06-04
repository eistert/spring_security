package com.security.core.social.qq.connet;

import com.security.core.social.qq.api.QQ;
import com.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;


/**
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId = "100550231";

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {

        super(new QQOAuth2Template(appId, "69b6ab57b22f3c2fe6a6149274e3295e", URL_AUTHORIZE, URL_ACCESS_TOKEN));

    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }

}
