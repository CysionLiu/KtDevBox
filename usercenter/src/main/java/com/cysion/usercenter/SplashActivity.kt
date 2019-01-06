package com.cysion.usercenter

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.cysion.other.startActivity_ex
import com.cysion.usercenter.R
import com.cysion.usercenter.ui.activity.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                startActivity_ex<MainActivity>()
                finish()
            }, 1000
        )
    }
}
