package com.example.android.location

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val status = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (status == PackageManager.PERMISSION_GRANTED) {
            accessLocations()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 11)
        }


    }//oncreate

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
    accessLocations()
}



    }
    fun accessLocations() {
        val lManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 1f, object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                if (location != null) {
                    textView.setText("Latitude:${location.latitude}")
                    textView2.setText("Longitude:${location.longitude}")
                }
            }

            override fun onProviderEnabled(p0: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderDisabled(p0: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })
    }

}