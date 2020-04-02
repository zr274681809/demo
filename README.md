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
- fun login(@Query("account") account: String, @Query("password") password: String): Call<User>
  - GET请求，参数account以及password ，返回值{User}
  - 登陆成功，设置User.backInfo = "TC001"，并返回
  - 登陆异常，设置User.backInfo = "TC002"，并返回
  - 无此账号，设置User.backInfo = "TC003" 并返回
 
### 注册Api 
- @POST("api/user")
- fun register(@Body userRegister: Model.UserRegister):Call<User>
  - POST请求，打包userRegister数据发送至服务器，返回值为{User}
  - 注册成功，设置User.backInfo = "TC101"后返回
  - 异常失败，设置User.backInfo = "TC102"后返回
  
### 注销Api
- @DELETE("api/user/{account}")
- fun logout(@Path("account") account:String):Call<Model.BackInfo>
  - DELETE请求，传入account,返回{Model.BackInfo}
  - 注销成功，设置Model.BackInfo.describe = "TC201"后返回
  - 异常失败，设置Model.Backinfo.describe = "TC202"后返回
  - 无此账号，设置Model.backInfo.describe = "TC203"后返回
  
### 更新用户信息Api
- @PUT("api/user/{account}")
- Call<Model.BackInfo> updateUser()
- 参数1：@Path(String account)  参数2： @PartMap Map<String,RequestBody> content
   - PUT请求，图文文字同时上传，返回{Model.BackInfo} 
   - 成功，设置Model.BackInfo.describe="TC301"后返回
   - 异常失败,设置Model.BackInfo.describe = "TC302"后返回
   - 无此账号,设置Model.BackInfo.describe = "TC303"后返回
   

## 动态模块  
### 动态模块主要实现包括用户图文或者视频内容的上传和删除以及查询，发布内容按时间排序，查询内容按发布时间获取
### 图文或者视频文字上传API
- @PUT("api/user/dynamic/{account}")
- Call<Model.BackInfo> onLoadDynamic()
- 参数1：@Path("account") (String account) 参数2：@path("groupId") Int groupId
- 参数3： @PartMap Map<String,RequestBody> content
  - PUT请求，支持图文或者视频文字上传，返回<Model.BackInfo>
  - 成功，设置Model.BackInfo.describe = "TC401"后返回
  - 异常失败，设置Model.BackInfo.describe = "TC402"后返回
  - 无此账号，设置Model.BackInfo.describe = "TC403"后返回
 
### 删除动态API
-@DELETE("api/user/dynamic/{account}")
-Call<Model.BackInfo> deleteDynamic()
-参数：@Path("account") String account 
  - DELETE请求，删除动态，返回<Model.BackInfo>
  - 成功，设置Model.BackInfo.describe = "TC501"后返回
  - 异常失败，设置Model.BackInfo.describe = "TC502"后返回
  - 无此账号，设置Model.BackInfo.describe = "TC503"后返回
 
### 查询动态API
-@GET("api/user/{groupId}/dynamic/{account}")
-Call<List<PersonDynamic>> getDynamics() 
- 参数1：@Query("limit") Int numbers 参数2:@Path("groupId") Int groupId
- 参数3：@Path("account") String account
  - GET请求，获取所属groupId的指定account好友的 limit = numbers 条动态，返回{List<PersonDynamic>}
  - groupID
  - 成功设置PersonDynamic.backInfo = "TC601"后返回
  - 无次
  
## 评论模块

