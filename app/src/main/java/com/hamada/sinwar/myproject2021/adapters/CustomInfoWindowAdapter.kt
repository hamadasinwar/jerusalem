package com.hamada.sinwar.myproject2021.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.models.MyMarker
import kotlinx.android.synthetic.main.info_window.view.*

class CustomInfoWindowAdapter(context: Context, val markers:MutableList<Marker>,
                              val cityInfo:MutableList<MyMarker>): GoogleMap.InfoWindowAdapter {

    private val view = LayoutInflater.from(context).inflate(R.layout.info_window, null)

    private fun renderInfoWindow(marker: Marker){
        var myMarker:MyMarker? = null
        for (mm in cityInfo){
            if (marker.position == LatLng(mm.lat!!, mm.long!!)){
               myMarker = mm
            }
        }
        view.infoTitle.text = myMarker?.title
        view.infoText.text = myMarker?.snippet
        Glide.with(view).asDrawable().apply(RequestOptions().override(200, 200).centerCrop()).load(myMarker?.image).into(view.infoImage)
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }

    override fun getInfoContents(p0: Marker): View? {
        renderInfoWindow(p0)
        return view
    }
}