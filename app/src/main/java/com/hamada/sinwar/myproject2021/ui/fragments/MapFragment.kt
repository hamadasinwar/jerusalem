package com.hamada.sinwar.myproject2021.ui.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.CustomInfoWindowAdapter
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.models.MyMarker
import com.hamada.sinwar.myproject2021.ui.activities.ImagePreviewActivity

class MapFragment : Fragment(R.layout.fragment_map) {

    lateinit var app: NewsApplication
    private lateinit var cityInfo:MutableList<MyMarker>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        app = requireActivity().application as NewsApplication
        cityInfo = app.cityInfo
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style_light))
        val jerusalem = LatLng(31.78756185955402, 35.231372234103205)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jerusalem, 14F))
        for (m in cityInfo){
            if (m.lat != null && m.long!= null) {
                val marker = LatLng(m.lat!!, m.long!!)
                googleMap.addMarker(MarkerOptions().position(marker))
            }
        }
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(requireContext(), cityInfo))
        googleMap.uiSettings.isMapToolbarEnabled = false
        googleMap.setMinZoomPreference(13.0f)
        googleMap.isBuildingsEnabled = false

        googleMap.setOnInfoWindowClickListener { marker->
            for (m in cityInfo){
                if (m.lat != null && m.long != null){
                    if (LatLng(m.lat!!, m.long!!) == marker.position){
                        val intent = Intent(requireContext(), ImagePreviewActivity::class.java)
                        intent.putExtra("preview", "${m.image}")
                        val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
                        startActivity(intent, options.toBundle())
                    }
                }
            }
        }
    }
}