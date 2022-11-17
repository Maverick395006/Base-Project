package com.maverick.baseproject.di

import android.content.Context
import androidx.room.Room
import com.maverick.baseproject.room.CountryDao
import com.maverick.baseproject.room.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideCountryDatabase(@ApplicationContext context: Context): CountryDatabase =
        Room.databaseBuilder(context, CountryDatabase::class.java, CountryDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideCountryDao(countryDatabase: CountryDatabase): CountryDao =
        countryDatabase.countryDao()

}