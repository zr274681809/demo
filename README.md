# TravelCommunityService
# 项目服务器
## 暂时实现部分功能
  - 实现用户登陆，注册，设置用户基本信息包括昵称，年龄，头像，爱好等等
  - 实现用户发送自身动态信息，包括文字、图片以及视频动态信息的发表，类似微信朋友圈
  - 实现用户互相评论、点赞，参考微信朋友圈的评论和点赞。
  - 实现好友查询，添加好友，按好友分组显示动态信息
## 数据库表 
  - user   用户表，保存了用户相关信息
  - userRelation 用户关系表，保存了用户之间的相互关系
  - commentsMsg   评论表 ,保存了所有用户评论信息
  - personDynamic  朋友动态表 ,保存了所有注册用户发出的朋友圈动态
  - DynamicAccessShield 朋友圈屏蔽表
  - DynamicAccessAllow 朋友圈开放表
  - like 朋友点赞表
### 数据库相关的模块见data/db/entity.kt
## 用户模块 
```
### 登录Api
- @GET("api/user")
- User  login()
- 参数1：@Query("account") String
- 参数2：@Query("password") String  
  - GET请求，返回值{User}
  - 设置好相应HTTP状态码
```
```
### 注册Api 
- @POST("api/user")
- User register()
- 参数：@Body("user") User
  - POST请求，返回值{User}
  - 设置好相应HTTP状态码
```
```
### 注销Api
- @DELETE("api/user/{account}")
- void logout()
- 参数：@Path("account") String
  - DELETE请求,返回HTTP状态码
 ```
 ```
### 更新用户信息Api
- @POST("api/user/{account}")
- void updateUser()
- 参数1：@Path("account") String
- 参数2：@PartMap Map<String,RequestBody> contentArgs
   - POST请求，支持图文同时上传
```
```
### 查询好友APi
- @GET("api/user/{userInfo}")
- User queryUser()
- 参数：@Path("userInfo") String
  - GET请求，userInfo可以是昵称，返回{User}
```
```
### 添加好友附带确认APi
- @POST("api/user/{friendAccount}")
- void addUserWithBack()
- 参数1: @Path("friendAccount") String
- 参数2：@QueryMap Map<String,String> addWithBackArgs
- addWithBack包含{userAccount="用户账号",groupId="设置所属分组ID",isRequireUser="是否是发起人"}
  - POST请求，friendAccount为对方账号，userAccount为用户账号
```  
```
### 删除好友Api
- DELETE("api/user/{friendAccount}")
- String  deleteFriend()
- 参数：@PATH("friendAccount") String
  - DELETE请求，删除指定账户好友，返回{String}
  - 成功，设置String = "601"后返回
  - 失败，设置String = "602"后返回
```
### 获取用户好友APi
- @GET("api/user/lists")
- String getFriends()
- 参数1：@Query("account") String
  - GET请求，获取account账户的好友，返回{String}
  - 成功，设置String = "TC701" + Content 后返回
  - 失败，设置String = "TC702"后返回
  - Content为获取到的好友列表内容
## 动态模块  
### 动态模块主要实现包括用户图文或者视频内容的上传和删除以及查询，发布内容按时间排序，查询内容按发布时间获取
### 参数说明
- permissionId表示朋友圈访问权限类型
- when(permissionId)
- == 0 -> 所有用户都可见
- == 1 -> 指定好友可见
- == 2 -> 除了指定好友，其它都可见 

### 添加动态API
- @POST("api/user/dynamic")
- String upLoadDynamic()
- 参数1：@QueryMap Map<String,String> permissionArgs 
- 参数2：@PartMap Map<String,RequestBody> contentArgs
- permissionArgs表示访问权限列表,user_n表示指定的用户，user_n如果没有，则表示均可见
- {account="用户账号",permissionId="权限类型",user_1="",user_2="",...}
- contentArgs表示内容列表，包含图片、文字、视频上传的RequestBody
  - POST请求，支持图文或者视频文字同时上传
### 删除动态API
- @DELETE("api/user/dynamic/{dynamicId}")
- String deleteDynamic()
- 参数：@Path("dynamicId") Int
 
### 查询动态API
- @GET("api/user/dynamic")
- String getDynamics() 
- 参数：@QueryMap  Map<String,String>  queryDynamicArgs   
- 参数包含内容：{userAccount="本机用户名"}{friendAccount="查询哪个用户动态"},{limitNumbers="动态数量"}
- 当{userAccount.isEmpty == true && friendAccount.isEmpty == true}时，查询系统推荐的按时间排序的limitNumbers条用户动态 {推荐页}
- 当{userAccount.isEmpty == false && friendAccount.isEmpty == true}时，查询friendAccount用户按系统推荐的limitNumbers条动态  {关注页}
- 当{userAccount.isEmpty == false && friendAccount.isEmpty == false}时，查询friendAccount用户的limitNumbers条动态  {用户页}  
  - GET请求，返回{List<PersonDynamic>}
  
## 评论模块 
### 添加评论APi
- @POST("api/user/comments")
- String comments()
- 参数1: @Body("message") String
- 参数2: @QueryMap Map<String,String> commentsArgs
- 参数commentsArgs包含
- {dynamicId="评论哪一条", userNickName="评论人的昵称"}
- {friendNickName="被评论人的昵称"，time="评论时间"}
  - POST请求，返回{String}
  - 成功，设置String = "TC1101"后返回
  - 失败,设置String = "TC1102" 后返回
### 查询评论APi 
- @GET("api/user/comments")
- String queryComments()
- 参数: @QueryMap queryCommentsArgs
- queryCommentsArgs主要有{myAccount="我的账号"，dynamicId="查询的动态id"，limitNumbers="获取评论的数量"}
  - 通过查询访问控制列表，获得可访问的用户账户列表,然后查询属于dynamicId的评论人
  - GET请求，返回{String}
  - 成功，设置String = "TC1201" + Content 后返回
  - 失败，设置String = "TC1202" 后返回
  - Content为CommentsMsg中的相关数据内容
### 删除评论APi
- @DELETE("api/user/comments")
- String deleteComments()
- 参数：@Query("id") Int 
  - Delete请求，删除指定评论，返回{String}
  - 成功，设置String = "TC1301"后返回
  - 失败，设置String = "TC1302"后返回
 ## 点赞模块
 ### 点赞APi
 - @POST("api/user/like")
 - String addLike()
 - 参数：@QueryMap Map<String，String> likeArgs
 - likeArgs={dynamicId= "",account= ""}
 - POST请求，返回{String}
 - 成功，设置String = "TC1401"后返回
 - 失败，设置String = "TC1402"后返回
 ### 取消点赞APi
 - @DELETE("api/user/like")
 - String cancelLike()
 - 参数：@Query("dynamicId") String
   - DELETE请求，删除指定dynamic的点赞，返回{String}
   - 成功，设置String = "TC1501"后返回
   - 失败，设置String = "TC1502"后返回
### 查询点赞APi
- @GET("api/user/like")
- String queryLike()
- 参数：@QueryMap Map<String,String> queryLikeArgs
- queryLikeArgs = {dynamicId="哪条动态",account = "谁请求查询"}
  - GET请求，返回<String>
  - 成功，设置String = "TC1601"+Content后返回
  - 失败，设置String = "TC1602"后返回
  - Content为Like表中的格式内容
 
