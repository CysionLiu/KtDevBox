package com.cysion.media.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.media.constant.Urls.SERVER_CHNS
import com.cysion.media.entity.ChannelList
import io.reactivex.Observable
import retrofit2.http.GET

object ChannelCaller : BaseCaller<ChnApi>(SERVER_CHNS, ChnApi::class.java,false)
interface ChnApi {

    @GET("musicBroadcasting")
    fun getChannelList(): Observable<BaseResp<MutableList<ChannelList>>>

}