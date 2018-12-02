package com.cysion.temp

import android.content.Context
import android.widget.Toast
import com.cysion.ktbox.Box
import com.cysion.ktbox.base.BaseActivity
import com.cysion.ktbox.net.BaseCaller
import com.cysion.ktbox.utils.RxTransformer
import com.cysion.ktbox.utils.logd
import com.cysion.ktbox.utils.logi
import com.cysion.targetfun._subscribe
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.http.GET

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        Box.init(self.applicationContext, true)
        tvShow.setOnClickListener {
            shortTip("hello KtDevBox")
            Caller.init()
            Caller.api.test().compose(RxTransformer.mainIo())
                ._subscribe {
                    _onNext {
                        logi(it)
                    }
                    _onError {
                        logi(it.message!!)
                    }
                }
        }
    }
}

fun Context.shortTip(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

const val BASEURL = "https://api.apiopen.top/"
object Caller : BaseCaller<Api>(BASEURL, Api::class.java)
interface Api {
    @GET("singlePoetry")
    fun test(): Observable<String>
}