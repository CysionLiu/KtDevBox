package com.cysion.media.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.media.entity.Data
import com.cysion.media.entity.Result
import io.reactivex.Observable
import retrofit2.http.GET

object MediaCaller : BaseCaller<NewsApi>("https://www.apiopen.top/", NewsApi::class.java)
interface NewsApi {
    @GET("journalismApi")
    fun getNewList(): Observable<Result<Data>>
}