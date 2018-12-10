package com.cysion.media.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.ktbox.net.BaseResponse
import com.cysion.media.constant.SERVER_NEWS
import com.cysion.media.entity.Data
import io.reactivex.Observable
import retrofit2.http.GET

object MediaCaller : BaseCaller<NewsApi>(SERVER_NEWS, NewsApi::class.java)
interface NewsApi {
    @GET("journalismApi")
    fun getNewList(): Observable<BaseResponse<Data>>

}

