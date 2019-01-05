package com.cysion.usercenter.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.ktbox.net.BaseResponse
import com.cysion.usercenter.entity.*
import io.reactivex.Observable
import retrofit2.http.*

object UserCaller : BaseCaller<UserApi>(UserUrls.HOST, UserApi::class.java, false)
interface UserApi {

    //    顶部轮播
    @GET("toploopers")
    fun getCarousel(): Observable<BaseResponse<MutableList<Carousel>>>

    //注册
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password2") password2: String
    ): Observable<BaseResponse<UserEntity>>

    //登录
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<BaseResponse<UserEntity>>


    //更新用户信息
    @FormUrlEncoded
    @POST("updateuser")
    fun updateUserInfo(
        @Field("nickname") nickname: String,
        @Field("desc") desc: String,
        @Field("avatar") avatar: String = ""
    ): Observable<BaseResponse<UserEntity>>

    //获取用户详情
    @POST("userdetail")
    fun getUserInfo(): Observable<BaseResponse<UserEntity>>

//    以下，博客相关

    //获取博客列表，时间顺序
    @GET("blog/list")
    fun getBlogList(@Query("page") page: Int = 1): Observable<BaseResponse<MutableList<Blog>>>


    //获取某个用户的博客
    @GET("blog/userlist")
    fun getUserBlogList(@Query("page") page: Int): Observable<BaseResponse<MutableList<Blog>>>

    //获取某个博客详情
    @GET("blog/get/{blogId}")
    fun getBlog(@Path("blogId") blogId: String): Observable<BaseResponse<Blog>>

    //删除博客
    @FormUrlEncoded
    @POST("blog/del")
    fun delBlog(@Field("blogId") blogId: String): Observable<BaseResponse<Any?>>


    //创建博客
    @FormUrlEncoded
    @POST("blog/add")
    fun createBlog(@Field("title") title: String, @Field("text") text: String): Observable<BaseResponse<Any?>>


    //更新博客
    @FormUrlEncoded
    @POST("blog/update")
    fun updateBlog(
        @Field("title") title: String, @Field("text") text: String
        , @Field("blogId") blogId: String
    ): Observable<BaseResponse<Any?>>


    //点赞博客
    @FormUrlEncoded
    @POST("blog/pride")
    fun prideBlog(@Field("blogId") blogId: String): Observable<BaseResponse<Any?>>


    //取消点赞博客
    @FormUrlEncoded
    @POST("blog/unpride")
    fun unPrideBlog(@Field("blogId") blogId: String): Observable<BaseResponse<Any?>>

    //收藏博客
    @FormUrlEncoded
    @POST("blog/collect")
    fun collectBlog(@Field("itemId") blogId: String): Observable<BaseResponse<Any?>>


    //取消收藏博客
    @FormUrlEncoded
    @POST("blog/uncollect")
    fun unCollectBlog(@Field("itemId") blogId: String): Observable<BaseResponse<Any?>>


    //博客收藏列表
    @POST("blog/collections")
    fun getCollectList(@Query("colType") colType: String = "0"): Observable<BaseResponse<MutableList<CollectEntity>>>


    //评论博客
    @FormUrlEncoded
    @POST("blog/comment")
    fun commentBlog(
        @Field("parentId") parentId: String,
        @Field("content") content: String
    ): Observable<BaseResponse<Any?>>


    //获取评论列表，时间顺序
    @GET("blog/comments/list")
    fun getComments(@Query("parentId") parentId: String): Observable<BaseResponse<MutableList<CommentEntity>>>


}