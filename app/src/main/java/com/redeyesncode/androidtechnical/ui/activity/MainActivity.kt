package com.redeyesncode.androidtechnical.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.redeyesncode.androidtechnical.R
import com.redeyesncode.androidtechnical.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed(Runnable {

            startActivity(Intent(this@MainActivity, DashboardActivity::class.java))

        },1500)
        setContentView(R.layout.activity_main)

    }
}