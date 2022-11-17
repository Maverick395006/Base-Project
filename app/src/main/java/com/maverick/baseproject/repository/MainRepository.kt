package com.maverick.baseproject.repository

import android.util.Log
import com.google.gson.Gson
import com.maverick.baseproject.model.Country
import com.maverick.baseproject.network.CountryRetrofit
import com.maverick.baseproject.network.NetworkMapper
import com.maverick.baseproject.room.CacheMapper
import com.maverick.baseproject.room.CountryDao
import com.maverick.baseproject.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository
constructor(
    private val countryDao: CountryDao,
    private val countryRetrofit: CountryRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
) {
    suspend fun getCountryList(): Flow<DataState<List<Country>>> = flow {
        emit(DataState.Loading)
        delay(1500)
        try {
            val networkCountryList = countryRetrofit.get()
            Log.e("API Response", Gson().toJson(networkCountryList))

            val countryData :List<Country> = networkMapper.mapFromEntityList(networkCountryList)
            Log.e("Mapped Response", countryData.toString())
            for (country in countryData) {
                countryDao.insert(cacheMapper.mapToEntity(country))
            }
            val cacheCountry = countryDao.get()
            Log.e("DB Response", cacheCountry.toString())
            val mCountryData :List<Country> = cacheMapper.mapFromEntityList(cacheCountry)
            emit(DataState.Success(mCountryData))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}