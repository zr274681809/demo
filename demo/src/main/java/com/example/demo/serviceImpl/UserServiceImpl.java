package com.example.demo.serviceImpl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
/** @author lyn 
 * @description //TODO 用户服务实现
 * @date 2020/4/7 15:19 
*/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    HttpSession session;

    @Override
    public String login(String account, String password) {
        User user1 = (User) session.getAttribute("user");
        if (user1!=null){
            return "已登录";
        }
        //初次跳转
        if(account==null || password==null || ("".equals(account) ||"".equals(password))){
            return "TC002";
        }
        password = MD5Utils.MD5(password);
        UsernamePasswordToken token = new UsernamePasswordToken(account,password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //主体提交登录请求到SecurityManager
            currentUser.login(token);
        }catch (IncorrectCredentialsException ice){

        }catch(UnknownAccountException uae){

        }catch (Exception e){
        }
        currentUser.getSession().setTimeout(-1000L);
        User user = userMapper.selectUserBynameAndPassword(account,password);
        if(currentUser.isAuthenticated()) {
            session.setAttribute("user",user);
            return "TC001";
        }
        else{
            return "TC003";
        }
    }





    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }

    @Override
    public String register(User user) {
        user.setPassword(MD5Utils.MD5(user.getPassword()));
        boolean b=  userMapper.insertNewOne(user);
        if (b){
            return "TC101";
        }
        return "TC102";
    }

}
