package com.maverick.baseproject.repository

import android.util.Log
import com.google.gson.Gson
import com.maverick.baseproject.model.Country
import com.maverick.baseproject.network.CountryRetrofit
import com.maverick.baseproject.network.NetworkMapper
import com.maverick.baseproject.room.CacheMapper
import com.maverick.baseproject.room.CountryDao
import com.maverick.baseproject.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository
constructor(
    private val countryDao: CountryDao,
    private val countryRetrofit: CountryRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
) {

    suspend fun getCountryList(): Flow<DataState<List<Country>>> = flow {

        // Loading State
        emit(DataState.Loading)
        delay(1500)
        try {
            // get data by Api Call
            val networkCountryList = countryRetrofit.get()
            Log.e("API Response", Gson().toJson(networkCountryList))

            // Convert Remote entity -> domain entity
            val countryData :List<Country> = networkMapper.mapFromEntityList(networkCountryList)
            Log.e("Mapped Response", countryData.toString())

            // Add domain entity data -> Local DB with mapper.
            for (country in countryData) {
                countryDao.insert(cacheMapper.mapToEntity(country))
            }

            // get data from Local DB
            val cacheCountry = countryDao.get()
            Log.e("DB Response", cacheCountry.toString())

            // Convert Local DB entity -> domain entity
            val mCountryData :List<Country> = cacheMapper.mapFromEntityList(cacheCountry)

            // Success State: Finally emit data  as Single Source of truth
            emit(DataState.Success(mCountryData))
        } catch (e: Exception) {
            // Error State
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}