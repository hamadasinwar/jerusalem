package com.hamada.sinwar.myproject2021.repository

import com.hamada.sinwar.myproject2021.api.RetrofitInstance
import com.hamada.sinwar.myproject2021.db.ArticleDatabase
import com.hamada.sinwar.myproject2021.models.Article

class NewsRepository(private val db: ArticleDatabase) {

    suspend fun getBreakingNews(pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(pageNumber, "popularity")

    suspend fun searchNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(countryCode, pageNumber)

    suspend fun replace(article: Article) = db.getArticleDao().replace(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}