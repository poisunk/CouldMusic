package com.example.couldmusic.page.main.activity

import com.example.couldmusic.base.BaseActivity
import android.os.Bundle
import com.example.couldmusic.R

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        Thread {
            try {
                Thread.sleep(500L)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            MainActivity.startMainActivity(this@StartActivity)
            finish()
        }.start()
    }
}