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
            val intent = Intent(this, WeatherInfoActivity::class.java)
            intent.putExtra("lat",37.5665) // North
            intent.putExtra("lon",126.9780) // East
            startActivity(intent)
        }

        button_newYork.setOnClickListener {
            val intent = Intent(this, WeatherInfoActivity::class.java)
            intent.putExtra("lat",40.7128)
            intent.putExtra("lon",-74.0060)
            startActivity(intent)
        }

        button_la.setOnClickListener {
            val intent = Intent(this, WeatherInfoActivity::class.java)
            intent.putExtra("lat",34.0522)
            intent.putExtra("lon",-118.2437)
            startActivity(intent)
        }

        button_london.setOnClickListener {
            val intent = Intent(this, WeatherInfoActivity::class.java)
            intent.putExtra("lat",51.5074)
            intent.putExtra("lon",-0.1278)
            startActivity(intent)
        }

        button_search.setOnClickListener {
            val intent = Intent(this, WeatherInfoActivity::class.java)
            val lat: Double = editText_latitude.text.toString().toDouble()
            val lon: Double = editText_longitude.text.toString().toDouble()
            intent.putExtra("lat", lat)
            intent.putExtra("lon", lon)
            startActivity(intent)
        }
    }
}
