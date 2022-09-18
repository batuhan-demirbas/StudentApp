package com.batuhandemirbas.studentapp

import android.app.Activity
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private val callback = OnMapReadyCallback { googleMap ->

        // EEM show map Latitude - Longitude
        val eem = LatLng(40.22569, 28.87568)
        googleMap.addMarker(MarkerOptions().position(eem).title("Elektrik-Elektronik Müh."))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eem, 15f))

        val yemekhane = LatLng(40.2248062,28.870331)
        googleMap.addMarker(MarkerOptions().position(yemekhane).title("Öğrenci Yemekhanesi"))

        val metro = LatLng(40.2190319,28.8691081)
        googleMap.addMarker((MarkerOptions().position(metro).title("Üniversite Metro Durağı")))

        // casting as
        locationManager = activity?.getSystemService(Activity.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                // Lokasyon değiştiğinde yapılacak işlemler


            }
        }
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