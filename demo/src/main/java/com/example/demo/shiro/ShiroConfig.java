package com.example.demo.shiro;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.CacheConfiguration;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/** @Author lyn
 * @Description //TODO 权限配置
 * @Date 2020/4/1 15:19
 */
@Configuration
public class ShiroConfig {

    @Bean("authorizer")
    public Authorizer authorizer(){
        return new ModularRealmAuthorizer();
    }
    /**
     * 配置Shiro核心 安全管理器 SecurityManager
     * SecurityManager安全管理器：所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；负责与后边介绍的其他组件进行交互。（类似于SpringMVC中的DispatcherServlet控制器）
     */



    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        CacheManager cacheManager1 = new CacheManager();
        CacheConfiguration ca = new CacheConfiguration();
        ca.setName("authenticationCache");
        ca.setMaxEntriesLocalHeap(2000);
        ca.eternal(false);
        ca.timeToIdleSeconds(0);
        ca.timeToLiveSeconds(0);
        ca.overflowToDisk(false);
        ca.statistics(true);
        CacheConfiguration ca1 = new CacheConfiguration();
        ca1.setName("authorizationCache");
        ca1.setMaxEntriesLocalHeap(2000);
        ca1.eternal(false);
        ca1.timeToIdleSeconds(0);
        ca1.timeToLiveSeconds(0);
        ca1.overflowToDisk(false);
        ca1.statistics(true);
        Ehcache cache = new Cache(ca);
        Ehcache cache1 = new Cache(ca1);
        cacheManager1.addCache(cache);
        cacheManager1.addCache(cache1);
        cacheManager.setCacheManager(cacheManager1);
        //   cacheManager.setCacheManagerConfigFile("classpath:shiro.xml");
        return cacheManager;
    }


    //  @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setRealm(getUserRealm());
        return securityManager;
    }

    @Bean
    public UserRealm getUserRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        //启用身份验证缓存，即缓存AuthenticationInfo信息，默认false
        userRealm.setAuthenticationCachingEnabled(true);
        //缓存AuthenticationInfo信息的缓存名称 在ehcache-shiro.xml中有对应缓存的配置
        userRealm.setAuthenticationCacheName("authenticationCache");
        //启用授权缓存，即缓存AuthorizationInfo信息，默认false
        userRealm.setAuthorizationCachingEnabled(true);
        //缓存AuthorizationInfo信息的缓存名称  在ehcache-shiro.xml中有对应缓存的配置
        userRealm.setAuthorizationCacheName("authorizationCache");
        return  userRealm;
    }


    /**
     * 配置Shiro的Web过滤器，拦截浏览器请求并交给SecurityManager处理
     * @return
     */

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean webFilter(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<>(16);
        //配置退出过滤器logout，由shiro实现
        filterChainMap.put("/logout","logout");
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
        filterChainMap.put("/api/user","anon");
   /*     filterChainMap.put("/inventory/login","anon");
        filterChainMap.put("/templates/**","anon");
        filterChainMap.put("/iclock/**","anon");
        filterChainMap.put("/static/**", "anon"); //匿名访问静态资源
        filterChainMap.put("/favicon.ico", "anon"); //图标
        filterChainMap.put("/CardService/**", "anon"); //匿名访问静态资源
        filterChainMap.put("/jquery-easyui-1.7.0/**", "anon"); //匿名访问静态资源*/
        filterChainMap.put("/**", "authc");
        //设置默认登录的URL.
        shiroFilterFactoryBean.setLoginUrl("/api/user");
        // shiroFilterFactoryBean.setUnauthorizedUrl("/templates/error.html");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }
}
