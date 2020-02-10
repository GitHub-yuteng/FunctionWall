package com.functionwall.config;

import com.functionwall.dao.UserMapper;
import com.functionwall.pojo.mould.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
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
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String account = usernamePasswordToken.getUsername();
        User user = Optional.ofNullable(getUserMapper().getUserByAccount(account)).orElse(null);
        //判断用户帐号是否存在
        if (null == user || !account.equals(user.getAccount())) {
            throw new UnknownAccountException();
        }
        //密码认证 shiro 接管
        return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), "");
    }
}