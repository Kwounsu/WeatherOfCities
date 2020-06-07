package com.example.weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("data/2.5/onecall")
//    @GET("data/2.5/onecall?lat=37.5665&lon=126.978&exclude=minutely,hourly,daily&appid=5d52492a88825d90aed4b60c70118855")
    fun getWeatherData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String
        ): Call<Data>
}