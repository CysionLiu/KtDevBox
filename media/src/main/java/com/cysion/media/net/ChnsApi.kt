package com.cysion.media.net

import com.cysion.ktbox.net.BaseCaller
import com.cysion.media.net.MediaUrls.SERVER_CHNS
import com.cysion.media.entity.ChannelList
import com.cysion.media.entity.SongList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

object ChannelCaller : BaseCaller<ChnApi>(SERVER_CHNS, ChnApi::class.java)
interface ChnApi {

    @GET("musicBroadcasting")
    fun getChannelList(): Observable<BaseResp<MutableList<ChannelList>>>

    @GET("musicBroadcastingDetails")
    fun getChannelDetail(@Query("channelname") channelname:String):Observable<BaseResp<SongList>>

}