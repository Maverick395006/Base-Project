package com.maverick.baseproject.room

import com.maverick.baseproject.model.Country
import com.maverick.baseproject.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<CountryCacheEntity, Country> {
    override fun mapFromEntity(entity: CountryCacheEntity): Country {
        return Country(
            countryName = entity.countryName,
            capital = entity.capital,
            currency = entity.currency,
            flagImage = entity.flag,
        )
    }

    override fun mapToEntity(domainModel: Country): CountryCacheEntity {
        return CountryCacheEntity(
            countryName = domainModel.countryName,
            capital = domainModel.capital,
            currency = domainModel.currency,
            flag = domainModel.flagImage
        )
    }

    fun mapFromEntityList(entities: List<CountryCacheEntity>): List<Country> {
        return entities.map { mapFromEntity(it) }
    }

}
