package com.hamada.sinwar.myproject2021.app

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.hamada.sinwar.myproject2021.models.NewsResponse
import com.hamada.sinwar.myproject2021.util.Resource

class NewsApplication:Application(){

    val cityInfo = mutableListOf<MyMarker>()
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

}