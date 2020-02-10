package com.functionwall.config;

import com.functionwall.dao.UserMapper;
import com.functionwall.pojo.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    protected UserMapper getUserMapper() {
        return userMapper;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String account = (String) usernamePasswordToken.getPrincipal();
        User user = Optional.ofNullable(getUserMapper().getUserByAccount(account)).orElse(null);
        //判断用户帐号是否存在
        if (null == user) {
            throw new UnknownAccountException();
        }
        //密码认证 shiro 接管
        return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(),((UsernamePasswordToken) authenticationToken).getUsername());
    }
}