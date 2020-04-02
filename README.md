# TravelCommunityService

# 项目服务器

## 暂时实现部分功能
  - 实现用户登陆，注册，设置用户基本信息包括昵称，年龄，头像，爱好等等
  - 实现用户发送自身动态信息，包括文字、图片以及视频动态信息的发表，类似微信朋友圈
  - 实现用户互相评论、点赞，参考微信朋友圈的评论和点赞。
  
## 数据库表 
  - user   用户表，保存了用户相关信息
  - userRelation 用户关系表，保存了用户之间的相互关系
  - commentsMsg   评论表 ,保存了所有用户评论信息
  - personDynamic  朋友动态表 ,保存了所有注册用户发出的朋友圈动态
  
### 数据库相关的模块见data/db/entity.kt

## 用户模块 

### 参数说明 
- Model.BackInfo.describe返回值分为两部分
- {Report+Content}字符串
- Report 指的是处理结果
- Content 指的是内容数据，需要将其处理成Josn的格式加到Report后面一起返回
- 因此，BackInfo返回内容 = Report + Content 
- 若需要传回必要数据，返回Report + Content 
- 若无有效数据或者不需要额外的回传参数，仅返回Report

### 登录Api
- @GET("api/user")
- Call<Model.BackInfo>  login()
- 参数1：@Query("account") String
- 参数2：@Query("password") String  
  - GET请求，返回值{Model.BackInfo}
  - 登陆成功，设置Model.BackInfo.describe = "TC001"+Content，并返回
  - 登陆异常，设置Model.BackInfo.describe = "TC002"，并返回
  - 无此账号，设置Model.BackInfo.describe = "TC003" 并返回
  - Content为User表中的基本信息，不需要回传密码
 
### 注册Api 
- @POST("api/user")
- Call<Model.BackInfo> register()
- 参数：@Body("user") User
  - POST请求,返回值{Model.BackInfo}
  - 注册成功，设置Model.BackInfo.describe = "TC101"后返回
  - 异常失败，设置Model.BackInfo.describe = "TC102"后返回
 
  
### 注销Api
- @DELETE("api/user/{account}")
- Call<Model.BackInfo> logout()
- 参数：@Path("account") String
  - DELETE请求，返回{Model.BackInfo}
  - 注销成功，设置Model.BackInfo.describe = "TC201"后返回
  - 异常失败，设置Model.Backinfo.describe = "TC202"后返回
  - 无此账号，设置Model.backInfo.describe = "TC203"后返回
  
  
### 更新用户信息Api
- @PUT("api/user/{account}")
- Call<Model.BackInfo> updateUser()
- 参数1：@Path("account") String
- 参数2：@PartMap Map<String,RequestBody> content
   - PUT请求，支持图文同时上传，返回{Model.BackInfo} 
   - 成功，设置Model.BackInfo.describe = "TC301"后返回
   - 异常失败,设置Model.BackInfo.describe = "TC302"后返回
   - 无此账号,设置Model.BackInfo.describe = "TC303"后返回
  

### 查询好友APi
- @GET("api/user/{userInfo}")
- Call<Model.BackInfo> queryUser()
- 参数：@Path("userInfo") String
  - GET请求，userInfo可以是昵称，可以是用户名,返回{Model.BackInfo}
  - 成功，设置Model.BackInfo.describe = "TC701" + Content
  - 无此用户，设置Model.BackInfo.describe = "TC702"
  - 异常失败，设置Model.BackInfo.describe = "TC703"
  - Content为好友的基本信息，不包含隐私信息如身份证，密码等等
  
### 添加好友APi
- @POST("api/user/{userAccount}")
- Call<Model.BackInfo> addUser()
- 参数1: @Path("userAccount") String
- 参数2：@Query("myAccount") String
  - POST请求，userAccount为对方账号，myAccount为发起者账号，返回{Model.BackInfo}
  - 成功,设置Model.BackInfo.describe = "TC801"
  - 异常失败,设置Model.BackInfo.describe = "TC802"
   

## 动态模块  
### 动态模块主要实现包括用户图文或者视频内容的上传和删除以及查询，发布内容按时间排序，查询内容按发布时间获取

### 参数说明
- groupId表示用户所属分组
- when(groupId)
- == 0 -> 属于根分组，所有用户都可见
- == others -> 属于用户自定义分组，仅该分组的人可见 

### 图文或者视频文字上传API
- @PUT("api/user/dynamic")
- Call<Model.BackInfo> onLoadDynamic()
- 参数1：@Query("account") String
- 参数2：@Query("groupId") Int
- 参数3：@PartMap Map<String,RequestBody> content
  - PUT请求，支持图文或者视频文字同时上传，返回<Model.BackInfo>
  - 成功，设置Model.BackInfo.describe = "TC401"后返回
  - 异常失败，设置Model.BackInfo.describe = "TC402"后返回
  - 无此账号，设置Model.BackInfo.describe = "TC403"后返回
 
### 删除动态API
-@DELETE("api/user/dynamic/{account}")
-Call<Model.BackInfo> deleteDynamic()
-参数：@Path("account") String
  - DELETE请求，删除动态，返回<Model.BackInfo>
  - 成功，设置Model.BackInfo.describe = "TC501"后返回
  - 异常失败，设置Model.BackInfo.describe = "TC502"后返回
  - 无此账号，设置Model.BackInfo.describe = "TC503"后返回
 
### 查询动态API
- @GET("api/user/{groupId}/dynamic/{account}")
- Call<List<PersonDynamic>> getDynamics() 
- 参数1：@Query("limitNumbers") Int 
- 参数2：@Query("dynamicId") Int
- 参数3: @Path("groupId") Int   
- 参数4: @Path("account") String  
- 参数使用说明
- 当{account.isEmpty == true}时，查询指定groupId的所有用户并按时间排序的limitNumbers条动态 
- 当{account.isEmpty == false}时，查询指定groupId的account用户的按时间排序的limitNumbers条动态
  - GET请求，获取所属groupId的指定account好友的 limitNumbers 条动态，返回{List<PersonDynamic>}
  - groupID
  - 成功设置PersonDynamic.backInfo = "TC601"后返回
  - 无次
 
## 评论模块

