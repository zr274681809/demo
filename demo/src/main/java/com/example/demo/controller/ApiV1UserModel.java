package com.example.demo.controller;


import com.example.demo.entity.Result;
import com.example.demo.entity.User;
import com.example.demo.service.AddFriendRecordService;
import com.example.demo.service.PersonDynamicService;
import com.example.demo.service.UserRelationService;
import com.example.demo.service.UserService;
import com.example.demo.utils.FdfsUtil;

import org.csource.common.MyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/** @author lyn
 * @description //TODO 用户模块
 * @date 2020/4/7 15:17
*/
@RestController
public class ApiV1UserModel {
    @Resource
    UserService userService;
    @Resource
    PersonDynamicService personDynamicService;
    @Resource
    UserRelationService userRelationService;
    @Resource
    AddFriendRecordService addFriendService;
/*lyn begin */
    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    public Result login(String account, String password){
        return new Result<>(userService.login(account, password));
    }

    @RequestMapping(value = "/api/register",method = RequestMethod.POST)
    public Result register(User user){
        return new Result<>(userService.register(user));
    }

    @RequestMapping(value = "/api/upload",method = RequestMethod.POST)
    public Result upload(MultipartFile file) throws IOException, MyException {
        return new Result<>(FdfsUtil.upLoad(file));
    }

    @RequestMapping(value = "/api/download",method = RequestMethod.GET)
    public void download(String filePath, HttpServletResponse response) throws IOException, MyException {
        response.getOutputStream().write(FdfsUtil.downLoad(filePath));
    }

    @RequestMapping(value = "/api/user/dynamic",method = RequestMethod.GET)
    public Result getDynamic(String userAccount,String friendAccount,Integer pageNumber){
        return new Result<>(personDynamicService.selectDynamicByAccount(userAccount,friendAccount,pageNumber));
    }

    @RequestMapping(value = "/api/user/{account}",method = RequestMethod.PUT)
    public Result updateUser(@PathVariable("account") String account,User user){
        user.setAccount(account);
        return  new Result<>(userService.updateUser(user));
    }

    @RequestMapping(value = "/api/user/{userInfo}",method = RequestMethod.GET)
    public Result queryUser(@PathVariable("userInfo") String userInfo){
        return new Result<>(userService.selectByUserNameOrNickName(userInfo));
    }

    @RequestMapping(value = "/api/user/{friendAccount}",method = RequestMethod.POST)
    public Result addUserWithBack(@PathVariable("friendAccount") String friendAccount,Integer groupId,Integer isRequireUser,String memo){
        return new Result<>(addFriendService.addUserWithBack(friendAccount,groupId,isRequireUser,memo));
    }

    @RequestMapping(value = "/api/user/{friendAccount}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable("friendAccount") String friendAccount){
        return new Result<>(userRelationService.deleteFriend(friendAccount));
    }

    @RequestMapping(value = "/api/user/friendLists" ,method = RequestMethod.GET)
    public Result getFriends(){
        return new Result<>(userRelationService.getFriends());
    }
    /*lyn end */
}
