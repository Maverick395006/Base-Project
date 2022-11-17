package com.maverick.baseproject.network

import retrofit2.http.GET

interface CountryRetrofit {

    @GET("v2/all")
    suspend fun get(): List<CountryNetworkEntity>

}