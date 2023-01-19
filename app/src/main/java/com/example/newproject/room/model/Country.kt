package com.example.newproject.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newproject.network.model.SalesArea
import com.example.newproject.network.model.SalesCountry
import com.example.newproject.network.model.SalesRegion
import com.example.newproject.network.model.SalesZone

@Entity
data class Country(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val country: String,
    val territory: String
)

fun SalesCountry.toDomainCountry(): Country {
    return Country(id = id, country = country, territory = territory)
}

@Entity
data class Zone(
    @PrimaryKey(autoGenerate = false)
    val zone: String, val territory: String
)

fun SalesZone.toDomainZone(): Zone {
    return Zone(
        zone = zone,
        territory = territory
    )
}

@Entity
data class Region(
    @PrimaryKey(autoGenerate = false)
    val region: String, val territory: String
)

fun SalesRegion.toDomainRegion(): Region {
    return Region(
        region = region, territory = territory
    )
}

@Entity
data class Area(
    @PrimaryKey(autoGenerate = false)
    val area: String,
    val territory: String
)

fun SalesArea.toDomainArea(): Area {
    return Area(
        area = area,
        territory = territory
    )
}
