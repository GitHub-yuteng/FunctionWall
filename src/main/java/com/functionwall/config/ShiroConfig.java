package com.functionwall.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yu
 */
@Configuration
public class ShiroConfig {

    //创建 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);


        //加载shiroFilter权限控制规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(loadShiroFilterChainDefinitionMap());

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");

        return shiroFilterFactoryBean;
    }

    //创建 DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建Realm对象
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        //设置加密算法
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法, 与注册时使用的散列算法相同
        hashedCredentialsMatcher.setHashIterations(2);// 散列次数, 与注册时使用的散列次数相同
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    public Map<String, String> loadShiroFilterChainDefinitionMap() {

        /**
         * anon: 无需认证就可以访问
         * authc：必须认证了才能访问
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限
         */

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/love-wall", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/assets/**", "anon");
        filterMap.put("/style.css", "anon");

        filterMap.put("/**", "authc");

        return filterMap;
    }
}
