@file:Suppress("DEPRECATION")

package com.hamada.sinwar.myproject2021.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.db.ArticleDatabase
import com.hamada.sinwar.myproject2021.repository.NewsRepository

class SplashActivity : AppCompatActivity() {

    lateinit var i:Intent
    lateinit var app:NewsApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        app = application as NewsApplication
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application as NewsApplication, newsRepository)
        app.viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val sharedPref = getSharedPreferences("com.hamada.sinwar", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        if (isFirstTime){
            i = Intent(this, OnBoardingActivity::class.java)
            editor.putBoolean("isFirstTime", false)
        }else{
            i = Intent(this, NewsActivity::class.java)
        }

        app.viewModel.getBreakingNews()

        Handler().postDelayed({
            startActivity(i)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }, 4000)

        editor.apply()
    }
}