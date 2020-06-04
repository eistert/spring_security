/**
 *
 */
package com.security.core.social.qq.connet;

import com.security.core.social.qq.api.QQ;
import com.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;


/**
 * @author zhailiang
 *
 */
public class QQAdapter implements ApiAdapter<QQ> {

    // 测试QQ服务 api是否可用
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        // 用户名
        values.setDisplayName(userInfo.getNickname());
        // 头像
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 主页url
        values.setProfileUrl(null);
        // 服务商的用户ID
        values.setProviderUserId(userInfo.getOpenId());
    }

    // 绑定解绑
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        // TODO Auto-generated method stub
        return null;
    }

    // 
    @Override
    public void updateStatus(QQ api, String message) {
        //do noting
    }

}
