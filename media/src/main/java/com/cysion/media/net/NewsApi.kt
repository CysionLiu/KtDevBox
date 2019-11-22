package com.cysion.media.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.media.entity.NewsInfoEntity
import com.cysion.media.net.MediaUrls.SERVER_NEWS
import io.reactivex.Observable
import retrofit2.http.GET

object MediaCaller : BaseCaller<NewsApi>(SERVER_NEWS, NewsApi::class.java)
interface NewsApi {
    @GET("getWangYiNews")
    fun getNewList(): Observable<BaseResp<ArrayList<NewsInfoEntity>>>
}

