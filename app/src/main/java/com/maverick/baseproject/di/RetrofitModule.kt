package com.maverick.baseproject.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.maverick.baseproject.network.CountryRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://restcountries.com/"

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun providesCountryService(retrofit: Retrofit.Builder): CountryRetrofit =
        retrofit.build().create(CountryRetrofit::class.java)

}