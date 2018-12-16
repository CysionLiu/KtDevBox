package com.cysion.usercenter.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.ktbox.net.BaseResponse
import com.cysion.usercenter.entity.Carousel
import io.reactivex.Observable
import retrofit2.http.GET

object UserCaller : BaseCaller<UserApi>("http://www.cysion.club/app/", UserApi::class.java)

interface UserApi {

    @GET("toploopers")
    fun getgetCarousel(): Observable<BaseResponse<MutableList<Carousel>>>

}