# TravelCommunityService
# 项目服务器
 ```
## 暂时实现部分功能
  - 实现用户登陆，注册，设置用户基本信息包括昵称，年龄，头像，爱好等等
  - 实现用户发送自身动态信息，包括文字、图片以及视频动态信息的发表，类似微信朋友圈
  - 实现用户互相评论、点赞，参考微信朋友圈的评论和点赞。
  - 实现好友查询，添加好友，按好友分组显示动态信息
 ```
 ```
## 数据库表 
  - user   用户表，保存了用户相关信息
  - userRelation 用户关系表，保存了用户之间的相互关系
  - commentsMsg   评论表 ,保存了所有用户评论信息
  - personDynamic  朋友动态表 ,保存了所有注册用户发出的朋友圈动态
  - like 朋友点赞表
```

```
## 数据请求返回结构
  - class ApiResponse<T>(
  - var data: T?,    //返回的数据内容
  - var errorCode: Int,  //错误代码   
  - var errorMsg: String  //错误的详细描述（一般设置为""即可）
  - )

## 服务器端状态码设置 
  - errorCode = {
  -  0 -> 成功
  -  1 -> 失败
  -  2 -> 无此查询结果
  -  other -> 其他情况，设置errorMsg以描述错误情况，视情况而定，以下内容不再赘述
  - }
```

### 数据库相关的模块见data/db/entity.kt

## 用户模块 
```
### 登录Api
- @GET("api/user")
- ApiResponse<User>  login()
- 参数1：@Query("account") String
- 参数2：@Query("password") String  
  - GET请求，返回值ApiResponse<User>
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
  - 用户名或者密码错误即表示无此查询内容，设置errorCode = 2
```
```
### 注册Api 
- @POST("api/user")
- ApiResponse<User> register()
- 参数：@Body("user") User
  - POST请求，返回值ApiResponse<User>
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```
```
### 注销Api
- /logout
  
 ```
 ```
### 更新用户信息Api
- PUT("api/user/{account}")
- ApiResponse updateUser()
- 参数1：@Path("account") String
- 参数2：@PartMap Map<String,RequestBody> contentArgs
   - PUT，支持图文同时上传，data设为null，返回ApiResponse
   - 成功设置errorCode = 0
   - 失败设置errorCode = 1
```
```
### 查询好友APi
- @GET("api/user/{userInfo}")
- ApiResponse<User> queryUser()
- 参数：@Path("userInfo") String
  - GET请求，userInfo可以是昵称，ApiResponse<User>
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
  - 无此查询内容 errorCode = 2
```
```
### 添加好友附带确认APi
- @FormUrlEncoded
- @POST("api/user/{friendAccount}")
- ApiResponse addUserWithBack()
- 参数1: @Path("friendAccount") String
- 参数2：@FieldMap Map<String,String> addWithBackArgs
- addWithBack包含{groupId="设置所属分组ID",isRequireUser="是否是发起人0是1不是"，memo="备注名"}
  - POST请求，data=null ,返回ApiResponse
  - friendAccount为对方账号
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```  
```
### 删除好友Api
- DELETE("api/user/{friendAccount}")
- ApiResponse  deleteFriend()
- 参数：@PATH("friendAccount") String
  - DELETE请求，删除指定账户好友，返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
  - 无此查询内容 errorCode = 2
```
```
### 获取当前用户的所有好友表APi
- @GET("api/user/friendLists")
- ApiResponse<List<UserRelation>> getFriends()
  - GET请求，获取当前账户的所有好友关系列表，返回 ApiResponse<List<UserRelation>>
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
  - 无此查询内容 errorCode = 2
 ```
## 动态模块  
### 动态模块主要实现包括用户图文或者视频内容的上传和删除以及查询，发布内容按时间排序，查询内容按发布时间获取
```
### 参数说明
- permissionId表示朋友圈访问权限类型
- when(permissionId)
- == 0 -> 所有用户都可见
- == 1 -> 指定好友可见
- == 2 -> 除了指定好友，其它都可见 
```
```
### 添加动态API
- @POST("api/user/dynamic")
- ApiResponse upLoadDynamic()
- 参数1：@FieldMap Map<String,String> permissionArgs 
- 参数2：@PartMap Map<String,RequestBody> contentArgs
- permissionArgs表示访问权限列表,user_n表示指定的用户，user_n如果没有，则表示均可见
- {account="用户账号",permissionId="权限类型",user_1="",user_2="",...}
- contentArgs表示内容列表，包含图片、文字、视频上传的RequestBody
  - POST请求，支持图文或者视频文字同时上传,返回ApiResponse
  - data = null
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
 ```
```
### 删除动态API
- @DELETE("api/user/dynamic/{dynamicId}")
- ApiResponse deleteDynamic()
- 参数：@Path("dynamicId") Int
  - DELETE请求，data=null,返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```
```
### 查询动态API
- @GET("api/user/dynamic")
- ApiResponse<List<PersonDynamic>> getDynamics() 
- 参数：@QueryMap  Map<String,String>  queryDynamicArgs   
- 参数包含内容：{userAccount="本机用户名"}{friendAccount="查询哪个用户动态"},{limitNumbers="动态数量"}
- 当{userAccount.isEmpty == true && friendAccount.isEmpty == true}时
- 查询系统推荐的按时间排序的limitNumbers条用户动态 {推荐页}
- 当{userAccount.isEmpty == false && friendAccount.isEmpty == true}时
- 查询friendAccount用户按系统推荐的limitNumbers条动态  {关注页}
- 当{userAccount.isEmpty == false && friendAccount.isEmpty == false}时
- 查询friendAccount用户的limitNumbers条动态  {用户页}  
  - GET请求，返回{ApiResponse<List<PersonDynamic>>}
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
 ```
 
## 评论模块
```
### 添加评论APi
- @POST("api/user/comments")
- ApiResponse comments()
- 参数1: @Body("message") String
- 参数2: @FieldMap Map<String,String> commentsArgs
- 参数commentsArgs包含
- {dynamicId="评论哪一条", userNickName="评论人的昵称"}
- {friendNickName="被评论人的昵称"，time="评论时间"}
  - POST请求，data=null,返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```
```
### 查询评论APi 
- @GET("api/user/comments")
- ApiResponse queryComments()
- 参数: @QueryMap queryCommentsArgs
- queryCommentsArgs主要有{myAccount="我的账号"，dynamicId="查询的动态id"，limitNumbers="获取评论的数量"}
  - 通过查询访问控制列表，获得可访问的用户账户列表,然后查询属于dynamicId的评论人
  - GET请求，data=null,返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```
```
### 删除评论APi
- @DELETE("api/user/comment/{id}")
- ApiResponse deleteComments()
- 参数：@Path("id") Int 
  - Delete请求，删除指定评论,data=null，返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```

 ## 点赞模块
 ```
 ### 点赞APi
 - @POST("api/user/like")
 - ApiResponse addLike()
 - 参数：@FieldMap Map<String，String> likeArgs
 - likeArgs={dynamicId= "",account= ""}
 - POST请求，data=null,返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```
```
 ### 取消点赞APi
 - @DELETE("api/user/like/{dynamicId}")
 - ApiResponse cancelLike()
 - 参数：@Path("dynamicId") String
   - DELETE请求，删除指定dynamicId的点赞，data=null,返回ApiResponse
   - 成功设置errorCode = 0
   - 失败设置errorCode = 1
```
```
### 查询点赞APi
- @GET("api/user/like")
- ApiResponse queryLike()
- 参数：@QueryMap Map<String,String> queryLikeArgs
- queryLikeArgs = {dynamicId="哪条动态",account = "谁请求查询"}
  - GET请求，data=null,返回ApiResponse
  - 成功设置errorCode = 0
  - 失败设置errorCode = 1
```
