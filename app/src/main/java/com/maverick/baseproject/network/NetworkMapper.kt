package com.maverick.baseproject.network

import com.maverick.baseproject.model.Country
import com.maverick.baseproject.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<CountryNetworkEntity,Country> {

    override fun mapFromEntity(entity: CountryNetworkEntity): Country {
        return Country(
            countryName = entity.countryName ?: "Anonymous Country",
            capital = entity.capital ?: "Anonymous capital",
            currency = entity.currencies?.get(0)?.symbol ?: "Anonymous currency",
            flagImage = entity.flags?.png ?: "https://flagcdn.com/w320/aq.png",
        )
    }

    override fun mapToEntity(domainModel: Country): CountryNetworkEntity {
        return CountryNetworkEntity(
            countryName = domainModel.countryName,
            capital = domainModel.capital,
            currencies = listOf(Currencies(domainModel.currency)),
            flags = Flags(domainModel.flagImage),
        )
    }

    fun mapFromEntityList(entities: List<CountryNetworkEntity>): List<Country> {
        return entities.map { mapFromEntity(it) }
    }

}