package com.hamada.sinwar.myproject2021.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.db.ArticleDatabase
import com.hamada.sinwar.myproject2021.repository.NewsRepository
import com.hamada.sinwar.myproject2021.ui.viewModel.NewsViewModel
import com.hamada.sinwar.myproject2021.ui.viewModel.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var messaging:FirebaseMessaging

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        messaging = Firebase.messaging

        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        val app = application as NewsApplication
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory =
            NewsViewModelProviderFactory(
                application as NewsApplication,
                newsRepository
            )
        app.viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

    }


}
