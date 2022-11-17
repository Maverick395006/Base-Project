package com.maverick.baseproject.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountryCacheEntity::class], version = 1)
abstract class CountryDatabase: RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object{
        const val DATABASE_NAME:String = "country_db"
    }

}