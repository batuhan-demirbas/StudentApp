package com.batuhandemirbas.studentapp

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->

        // EEM show map Latitude - Longitude
        val eem = LatLng(40.22569, 28.87568)
        googleMap.addMarker(MarkerOptions().position(eem).title("Elektrik-Elektronik Müh."))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eem, 15f))

        // Yemekhane show map Latitude - Longitude
        val yemekhane = LatLng(40.2248062,28.870331)
        googleMap.addMarker(MarkerOptions().position(yemekhane).title("Öğrenci Yemekhanesi"))

        // Metro show map Latitude - Longitude
        val metro = LatLng(40.2190319,28.8691081)
        googleMap.addMarker((MarkerOptions().position(metro).title("Üniversite Metro Durağı")))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}