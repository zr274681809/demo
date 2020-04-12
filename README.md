# TravelCommunityService
# 项目服务器

## 暂时实现部分功能
  - 实现用户登陆，注册，设置用户基本信息包括昵称，年龄，头像，爱好等等
  - 实现用户发送自身动态信息，包括文字、图片以及视频动态信息的发表，类似微信朋友圈
  - 实现用户互相评论、点赞，参考微信朋友圈的评论和点赞。
  - 实现好友查询，好友关注，好友权限管理，动态权限管理


## 数据库表 
  ### user   用户表，保存了用户相关信息
  ------------------------------------------------
    userId	
    account	用户名
    nickName	昵称
    age	年龄
    birthday	出生日期
    headPortraitUrl	头像图片地址
    phoneNumber	手机号码
    password	密码
    identifyNumber	身份证号码
    sex	性别
    hobby	爱好
    introduce	自我介绍
    stat	默认0可用1锁定
    lastLoginTime	最后登录时间
    isMember	普通设置为0 达人设置为1
    privateModel	隐私模式
    0表示开放，只要有人申请关注，就可以通过
    1表示私密，需要申请并由用户本人确认后，才可以通过
  --------------------------------------------------
  
  
  ### userRelation 用户关系表，保存了用户之间的相互关系
  --------------------------------------------------
    userid	当前用户user表主键	
    friendid	好友user表主键
    groupId	分组
    stat1	屏蔽状态默认0未屏蔽1屏蔽
    stat2	不让看状态默认0可以看1不让看
    memo	备注名
  --------------------------------------------------
  
  
  ### commentsMsg   评论表 ,保存了所有用户评论信息
  --------------------------------------------------
    id	
    dynamicId	动态id
    userId	评论人id
    userAccount	评论人的账号
    userNickName	评论人昵称
    friendNickName	被评论人昵称
    Msg	消息内容
    times	评论时间
  --------------------------------------------------
  
  
  ### personDynamic  朋友动态表 ,保存了所有注册用户发出的朋友圈动态
  --------------------------------------------------
    id	
    user_id	用户id
    textContent	内容
    videoUrl	视频url
    imageUrls	图片路径
    likesCount	点赞数
    commentsCount	评论数
    submitsTime	发布时间
    location	用户的位置
    fullWatchCount	完播次数统计
    backWatchCount	观看次数统计
    heatDegree	动态的火热程度  
    火热程度根据点赞数、评论数、完播次数以及回看次数来计算
    private	隐私设置
    0表示开放
    1表示仅关注的人可见
    2表示自定义，需通过查询权限表获取访问权限
    3表示私密，仅自己可见
  --------------------------------------------------
  ### like 朋友点赞表
  --------------------------------------------------
    dynamicId	动态
    userid	点赞用户
  --------------------------------------------------
  ### chat 聊天记录表
  --------------------------------------------------
    id	聊天记录ID
    userId	用户Id
    friendId	朋友用户Id
    content	这条对话内容
    time	对话时间
  --------------------------------------------------
  ### userDynamicPermission 用户动态权限表
  --------------------------------------------------
    userid	动态发布人Id
    dynamicId	动态Id
    friendId	申请查看动态的朋友Id
    permission	权限ID
  --------------------------------------------------
  ### addFriendRecord 好友添加记录
  --------------------------------------------------
    userid	发起用户
    friendid	被添加用户
    stat	默认0请求中1接受2拒绝
    time	请求时间
    groupid	分组id
    memo	备注名
  --------------------------------------------------




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
 ### 推荐页
   服务器根据全部用户动态ID的热度排序，随机获取动态权限为非私密的dynamicId以及userId。
   若该dynamicId的private为0(开放)，直接获取该动态，存入数组
   若该dynamicId的private为1（仅关注的人可见），先保存该动态，在userRelation中查找该dynamicId，其中friendAccount==发起请求用户的id时，将该动态存入数组。
   若该dynamicId的private为2（自定义），先保存该动态，在userDynamicPermission中通过dynamicId进行查找
     ------ 若查找到friendId == 申请人Id
            当permission==0时，表示可看，随后将该条动态存入数组
            当permission==1时，表示不可见，继续查找下一条
     ------ 若没有查找到对应的申请人Id,但存在该dynamicId中的其他用户的
            permission==0时，表示申请人被屏蔽，继续查找下一条
            permission==1时，表示指定该申请人可见，将动态存入数组。
  
### 关注页 
   服务器根据该申请人用户所关注好友中的动态ID的热度排序，随机获取动态权限为非私密的dynamicId以及userId。
   若该dynamicId的private为0(开放)或者1(仅关注的人可见)时，直接获取该动态，存入数组
   若该dynamicId的private为2（自定义）时，先保存该动态，在userDynamicPermission中通过dynamicId进行查找
   ------ 若查找到friendId == 申请人Id
          当permission==0时，表示可看，随后将该条动态存入数组
          当permission==1时，表示不可见，继续查找下一条
   ------ 若没有查找到对应的申请人Id,但存在该dynamicId中的其他用户的
          permission==0时，表示申请人被屏蔽，继续查找下一条
          permission==1时，表示指定该申请人可见，将动态存入数组。
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
