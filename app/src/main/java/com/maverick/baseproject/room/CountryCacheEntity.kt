package com.maverick.baseproject.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "countries", indices = [Index(value = ["countryName"], unique = true)])
data class CountryCacheEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "countryName")
    var countryName: String,

    @ColumnInfo(name = "capital")
    var capital: String,

    @ColumnInfo(name = "currency")
    var currency: String,

    @ColumnInfo(name = "flag")
    var flag: String,

    )