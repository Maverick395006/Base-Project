package com.maverick.baseproject.di

import com.maverick.baseproject.network.CountryRetrofit
import com.maverick.baseproject.network.NetworkMapper
import com.maverick.baseproject.repository.MainRepository
import com.maverick.baseproject.room.CacheMapper
import com.maverick.baseproject.room.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        countryDao: CountryDao,
        retrofit: CountryRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
    ): MainRepository = MainRepository(countryDao, retrofit, cacheMapper, networkMapper)

}