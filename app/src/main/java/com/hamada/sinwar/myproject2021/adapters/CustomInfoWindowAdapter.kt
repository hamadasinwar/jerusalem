package com.hamada.sinwar.myproject2021.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.info_window.view.*

class CustomInfoWindowAdapter(val context: Context, private val cityInfo:MutableList<MyMarker>): GoogleMap.InfoWindowAdapter {

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

        Picasso.get().load(myMarker?.image).resize(250, 250).centerCrop()
            .placeholder(R.drawable.the_rock_dome)
            .into(view.infoImage, object : Callback {
                override fun onSuccess() {
                    if (marker.isInfoWindowShown) {
                        marker.hideInfoWindow()
                        marker.showInfoWindow()
                    }
                }
                override fun onError(e: Exception?) {}
            })
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }

    override fun getInfoContents(p0: Marker): View? {
        renderInfoWindow(p0)
        return view
    }
}