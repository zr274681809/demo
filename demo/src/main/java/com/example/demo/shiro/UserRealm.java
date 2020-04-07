package com.example.demo.shiro;


import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


public class UserRealm extends AuthorizingRealm {

    @Resource
    UserService userService;

    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

            //获取登录用户
        //User user = (User) principalCollection.getPrimaryPrincipal();
        Set<String> roleNames =new HashSet<>();
        Set<String> permissions =new HashSet<>();
        //获取用户角色
      //  Role role = roleService.selectById(user.getRoleId());
    //    roleNames.add(role.getRolename());
        //获取用户权限
   //     List<RoleRight> roleRights = roleRightService.selectRights(role.getRoleId());
    //    for (RoleRight roleRight : roleRights) {
   //         String url = rightService.selectById(roleRight.getRightId()).getDescription();
    //        if(url!=null && !url.equals(""))
   //              permissions.add(url);
  //      }
        //添加角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        //添加权限
        info.setStringPermissions(permissions);
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户输入的账号
        String username = (String)token.getPrincipal();
        //2.通过username从数据库中查找到user实体
        User user = userService.selectByUserName(username);
    //通过用户名获取user
        if(user == null){
            return null;
        }
        //3.通过SimpleAuthenticationInfo做身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        //4.用户账号状态验证等其他业务操作

        //5.返回身份处理对象

        return simpleAuthenticationInfo;
    }
}
