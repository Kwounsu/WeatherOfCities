package com.example.weatherapp

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/onecall")
    fun getWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = "5d52492a88825d90aed4b60c70118855"
        ): Observable<Data>

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"

        private val client = OkHttpClient // OkHttp: efficient HTTP & HTTP/2 client
            .Builder()
            .build()

        private val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(WeatherService::class.java)

        fun buildService(): WeatherService {
            return retrofit
        }
    }
}