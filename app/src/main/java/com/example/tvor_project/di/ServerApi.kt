package com.example.tvor_project.di

import com.example.tvor_project.data_classes.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    @GET("api.php")
    suspend fun getSearchResult(@Query("query") query:String):Choices

    @GET("api?request=schedule")
    suspend fun getRaspisanie(@Query("group") group:String, @Query("week") week:Int):Schedule
}