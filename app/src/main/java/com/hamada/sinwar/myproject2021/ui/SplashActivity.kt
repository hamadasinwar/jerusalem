package com.hamada.sinwar.myproject2021.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hamada.sinwar.myproject2021.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    lateinit var i:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("com.hamada.sinwar", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        if (isFirstTime){
            i = Intent(this, OnBoardingActivity::class.java)
            editor.putBoolean("isFirstTime", false)
        }else{
            i = Intent(this, NewsActivity::class.java)
        }

        Handler().postDelayed({
            startActivity(i)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 4000)

        editor.apply()
    }
}