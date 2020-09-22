package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_weather_info.*
import kotlin.math.roundToLong

class WeatherInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        val bundle: Bundle? = intent.extras
        val lat = bundle?.get("lat") as Double
        val lon = bundle?.get("lon") as Double

        // Disposable: tracks the fetching activity. should fetching result return -> activity destroy
        // CompositeDisposable: class to keep all disposables in the same place to dispose all of then at once
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            WeatherService.buildService().getWeatherData(lat.toString(), lon.toString())
                // subscribeOn: Observable
                .subscribeOn(Schedulers.io()) // creates a Scheduler thread over which we do the network call. IO-bound work.
                // observeOn: Observer
                .observeOn(AndroidSchedulers.mainThread()) // fetched data to be displayed on the MainTread (UI)
                .subscribe(this::handleResponse, this::handleError) // execute api call
        )
    }

    private fun handleError(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }

    private fun handleResponse(response: Data) {
        Log.d("Retrofit", "result: $response")
        val Temp =  ((response.current.temp - 273.15) * 9 / 5 + 32).roundToLong()  // Kelvin to Fahrenheit
        textView_temp.text = Temp.toString()
        textView_timezone.text = response.timezone
        textView_humidity.text = response.current.humidity.toString()
        textView_wind.text = response.current.wind_speed.toString()
        textView_weather.text = response.current.weather.toString().substringAfter("main=").substringBefore(", description")
    }
}
