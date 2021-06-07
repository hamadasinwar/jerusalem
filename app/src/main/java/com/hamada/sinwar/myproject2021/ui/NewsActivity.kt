package com.hamada.sinwar.myproject2021.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.db.ArticleDatabase
import com.hamada.sinwar.myproject2021.repository.NewsRepository
import com.hamada.sinwar.myproject2021.ui.fragments.SearchNewsFragment
import kotlinx.android.synthetic.main.activity_news.*


class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        //for(int i = 0; i < menu.size(); i++){

        for (item in menu.iterator()){
            val drawable = item.icon
            if (drawable != null) {
                drawable.mutate()
                drawable.setColorFilter(resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        supportFragmentManager.beginTransaction().replace(R.id.flFragment, SearchNewsFragment())
            .addToBackStack("").commit()


        return super.onOptionsItemSelected(item)
    }
}
