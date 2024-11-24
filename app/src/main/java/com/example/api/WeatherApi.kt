package com.example.api

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeater(

        @Query("key") apikey : String,
        @Query("q") city : String
    ): Response<WeatherModel>

}