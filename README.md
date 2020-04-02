# TravelCommunityService
# 项目服务器
## 暂时实现部分功能
  - 实现用户登陆，注册，设置用户基本信息包括昵称，年龄，头像，爱好等等
  - 实现用户发送自身动态信息，包括文字、图片以及视频动态信息的发表，类似微信朋友圈
  - 实现用户互相评论、点赞，参考微信朋友圈的评论和点赞。
## 数据库表 
  - user   用户表，保存了用户相关信息
  - commentsMsg   评论表  保存了所有用户评论信息
  - personDynamic  朋友动态表  保存了所有注册用户发出的朋友圈动态
### 数据库相关的模块见data/db/entity.kt

## 用户模块 
### 登录Api
- @GET("api/user")
- fun login(@Query("account") account: String, @Query("password") password: String): Call<Model.UserInfo>
  - GET请求，参数account以及password ，返回值{Model.UserInfo}
  - 登陆成功，设置UserInfo.backInfo = "TC001"，并返回
  - 登陆异常，设置UserInfo.backInfo = "TC002"，并返回
  - 该用户不存在，设置UserInfo.backInfo = "TC003" 并返回
 
### 注册Api 
 - @POST("api/user")
 - fun register(@Body userRegister: Model.UserRegister):Call<Model.UserLogin>
  - POST请求，打包userRegister数据发送至服务器，返回值为{Model.UserLogin}
  - 注册成功设置UserLogin.backInfo = "TC101"后返回
  - 注册失败返回UserLogin.backInfo = "TC102"后返回
  
### 注销Api
 - @DELETE("api/user/{account}")
 - fun logout(@Path("account") account:String):Call<Model.BackInfo>
  - DELETE请求，传入account,返回{Model.BackInfo}
  - 注销成功，并设置Backinfo.describe = "TC201"后返回
  - 注销失败，并设置Backinfo.describe = "TC202"后返回
  
### 更新用户信息Api
 - @PUT("api/user/{account}")
 - fun updateUser(@Path("account") account:String,
 - @PartMap content:<String,RequestBody>):Call<{Model.BackInfo>
   - PUT请求，图文文字同时上传，返回{Model.BackInfo} 
   - 成功设置BackInfo.describe = "TC301"后返回
   - 失败设置BackInfo.describe = "TC302"后返回
   
### 网络相关的数据结构见客户端的data/network/Model.kt文件

## 动态模块  
### 动态模块主要实现包括用户图文或者视频内容的上传和删除以及查询，发布内容按时间排序，查询内容按发布时间获取
### 图文或者视频文字上传API
- @PUT("api/user/dynamic/{account}")
- PUT请求，

## 评论模块

