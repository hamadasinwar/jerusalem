package com.hamada.sinwar.myproject2021.app

import android.app.Application
import com.hamada.sinwar.myproject2021.models.MyMarker

class NewsApplication:Application(){

    val cityInfo = mutableListOf<MyMarker>()

}