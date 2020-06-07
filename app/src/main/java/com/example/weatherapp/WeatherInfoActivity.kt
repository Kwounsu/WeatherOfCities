package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_weather_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToLong

class WeatherInfoActivity : AppCompatActivity() {
    val BaseUrl = "https://api.openweathermap.org/"
    val appid = "5d52492a88825d90aed4b60c70118855"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_info)

        val bundle:Bundle? = intent.extras
        val lat = bundle?.get("lat") as Double
        val lon = bundle?.get("lon") as Double

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)

        service.getWeatherData(lat.toString(),lon.toString(),appid).enqueue(object : Callback<Data>{
//        service.getWeatherData(lat, lon).enqueue(object : Callback<Data>{
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("Retrofit", "result :" + t.message)
            }
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if(response.isSuccessful){
                    val weatherResponse = response.body()
                    Log.d("Retrofit", "result: " + weatherResponse.toString())
                    val Temp =  ((weatherResponse!!.current.temp - 273.15) * 9 / 5 + 32).roundToLong()  // Kelvin to Fahrenheit
                    textView_temp.text = Temp.toString()
                    textView_timezone.text = weatherResponse.timezone
                    textView_humidity.text = weatherResponse.current.humidity.toString()
                    textView_wind.text = weatherResponse.current.wind_speed.toString()
                    textView_weather.text = weatherResponse.current.weather.toString().substringAfter("main=").substringBefore(", description")
                } else{
                    Log.d("Retrofit", "Response not success $response")
                }
            }
        })
    }
}
