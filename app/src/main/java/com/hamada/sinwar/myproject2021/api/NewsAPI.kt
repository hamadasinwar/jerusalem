package com.hamada.sinwar.myproject2021.api

import com.hamada.sinwar.myproject2021.models.NewsResponse
import com.hamada.sinwar.myproject2021.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/everything?q=jerusalem")
    suspend fun getBreakingNews(
        @Query("page")
        pageNumber: Int = 1,
        @Query("sortBy")
        sortBy: String,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ): Response<NewsResponse>

}