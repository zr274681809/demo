# TravelCommunityService
项目服务器
## 暂时实现部分功能
- 实现用户登陆，注册，设置用户基本信息包括昵称，年龄，头像，爱好等等
- 实现用户发送自身动态信息，包括文字、图片以及视频动态信息的发表，类似微信朋友圈
- 实现用户互相评论、点赞，参考微信朋友圈的评论和点赞。
## 数据库表 
- user   用户表，保存了用户相关信息
- commentsMsg   评论表  保存了所有用户评论信息
- personDynamic  朋友动态表  保存了所有注册用户发出的朋友圈动态
## 用户模块 
### 登录Api
- @GET("api/user")
- fun login(@Query("account") account:String, @Query("password") password:String):Call<Model.UserInfo>
  - GET请求，参数account以及password 
  - 登陆成功返回Model.UserInfo,并设置backInfo = "TC001"
  - 登陆异常失败设置UserInfo.backInfo = "TC401"
  - 该用户不存在设置UserInfo.backInfo = "TC402"
  - 相关数据结构见客户端的data/network/Model.kt文件
### 注册Api 
  - @POST("api/user")
  - fun register(@Body userRegister: Model.UserRegister):Call<Model.UserLogin>
### 注销Api
  - @DELETE("api/user/{account}")
  - fun logout(@Path("account") account:String):Call<Model.Info>
### 更新用户信息Api
  - @PUT("api/user/{account}")
  - fun updateUser(@Path("account") account:String,@PartMap content: Map<String,RequestBody>):Call<Model.Info>
- 相关数据结构见Model文档 


