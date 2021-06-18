package com.hamada.sinwar.myproject2021.repository

import android.util.Log
import com.hamada.sinwar.myproject2021.api.RetrofitInstance
import com.hamada.sinwar.myproject2021.db.ArticleDatabase
import com.hamada.sinwar.myproject2021.models.Article

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun getBreakingNews(pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(pageNumber, "publishedAt")

    suspend fun replace(article: Article):Long {
        val l = db.getArticleDao().replace(article)
        Log.e("hmd", "Log: $l")
        return l
    }

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}