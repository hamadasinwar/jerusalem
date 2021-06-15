package com.hamada.sinwar.myproject2021.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.hamada.sinwar.myproject2021.R
import com.hamada.sinwar.myproject2021.adapters.CustomInfoWindowAdapter
import com.hamada.sinwar.myproject2021.app.NewsApplication
import com.hamada.sinwar.myproject2021.models.MyMarker

class MapFragment : Fragment(R.layout.fragment_map) {

    lateinit var app: NewsApplication
    lateinit var cityInfo:MutableList<MyMarker>
    lateinit var markers:MutableList<Marker>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        app = requireActivity().application as NewsApplication
        cityInfo = app.cityInfo
        markers = mutableListOf()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style))
        val jerusalem = LatLng(31.768307723966437, 35.21334980686194)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jerusalem, 12F))
        for (m in cityInfo){
            val marker = LatLng(m.lat!!, m.long!!)
            val mm = googleMap.addMarker(MarkerOptions().position(marker))
            markers.add(mm)
        }
        googleMap.setInfoWindowAdapter(CustomInfoWindowAdapter(requireContext(), cityInfo))
        googleMap.uiSettings.isMapToolbarEnabled = false
        googleMap.setMinZoomPreference(12.0f)
        googleMap.isBuildingsEnabled = false
    }
}