@file:Suppress("DEPRECATION")

package com.hamada.sinwar.myproject2021.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.db.ArticleDatabase
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.hamada.sinwar.myproject2021.models.Statics
import com.hamada.sinwar.myproject2021.repository.NewsRepository
import com.hamada.sinwar.myproject2021.ui.viewModel.NewsViewModel
import com.hamada.sinwar.myproject2021.ui.viewModel.NewsViewModelProviderFactory

class SplashActivity : AppCompatActivity() {

    lateinit var i:Intent
    lateinit var app:NewsApplication
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        db = Firebase.firestore
        app = application as NewsApplication
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory =
            NewsViewModelProviderFactory(
                application as NewsApplication,
                newsRepository
            )
        app.viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val sharedPref = getSharedPreferences("com.hamada.sinwar", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val isFirstTime = sharedPref.getBoolean("isFirstTime", true)

        FirebaseApp.initializeApp(this)
        db.collection("cityInfo").get().addOnSuccessListener { query->
            for (doc in query.documents){
                val marker = MyMarker(doc.id, doc.getString("title"), doc.getString("text"),
                    doc.getString("image"), doc.get("lat").toString().toDouble(),
                    doc.get("long").toString().toDouble())
                app.cityInfo.add(marker)
            }
        }

        db.collection("statics").get().addOnSuccessListener { query->
            for (doc in query.documents){
                val s = Statics(doc.id, doc.getString("text")!!, doc.get("progress").toString().toInt())
                app.staticsInfo.add(s)
                app.staticsInfo.add(s)
                app.staticsInfo.add(s)
                app.staticsInfo.add(s)
            }
        }

        if (isFirstTime){
            i = Intent(this, OnBoardingActivity::class.java)
            editor.putBoolean("isFirstTime", false)
        }else{
            i = Intent(this, NewsActivity::class.java)
        }

        if (sharedPref.getString("infoType", null) == null){
            editor.putString("infoType", "map")
        }

        app.viewModel.getBreakingNews()

        Handler().postDelayed({
            startActivity(i)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 4000)

        editor.apply()
    }
}