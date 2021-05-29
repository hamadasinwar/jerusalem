package com.hamada.sinwar.myproject2021.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamada.sinwar.myproject2021.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replace(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}