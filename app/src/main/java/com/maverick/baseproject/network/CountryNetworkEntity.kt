package com.maverick.baseproject.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryNetworkEntity(

    @SerializedName("name")
    @Expose
    var countryName: String? = null,

    @SerializedName("capital")
    @Expose
    var capital: String? = null,

    @SerializedName("flags")
    @Expose
    var flags: Flags? = null,

    @SerializedName("currencies")
    @Expose
    var currencies: List<Currencies>? = null,

    )

data class Flags(

    @SerializedName("png")
    @Expose
    var png: String? = null,

    )

data class Currencies(

    @SerializedName("symbol")
    @Expose
    var symbol: String? = null,

    )