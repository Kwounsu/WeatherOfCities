package com.example.weatherapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash Screen
        Handler().postDelayed({
            setTheme(R.style.AppTheme)
        }, 3000)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_seoul.setOnClickListener {
            startWeatherInfoActivityWIthLatAndLon(37.5665,126.9780)
        }

        button_newYork.setOnClickListener {
            startWeatherInfoActivityWIthLatAndLon(40.7128,-74.0060)
        }

        button_la.setOnClickListener {
            startWeatherInfoActivityWIthLatAndLon(34.0522,-118.2437)
        }

        button_london.setOnClickListener {
            startWeatherInfoActivityWIthLatAndLon(51.5074,-0.1278)
        }

        button_search.setOnClickListener {
            val lat: Double = editText_latitude.text.toString().toDouble()
            val lon: Double = editText_longitude.text.toString().toDouble()
            startWeatherInfoActivityWIthLatAndLon(lat,lon)
        }
    }

    private fun startWeatherInfoActivityWIthLatAndLon (lat: Double, lon: Double) {
        val intent = Intent(this, WeatherInfoActivity::class.java)
        intent.putExtra("lat",lat)
        intent.putExtra("lon",lon)
        startActivity(intent)
    }
}
