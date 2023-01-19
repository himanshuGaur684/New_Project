package com.example.newproject.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newproject.room.model.Area
import com.example.newproject.room.model.Country
import com.example.newproject.room.model.Region
import com.example.newproject.room.model.Zone

@Dao
interface MainDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: List<Country>)

    @Query("SELECT * FROM Country")
    suspend fun getCountryList(): List<Country>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegion(country: List<Region>)

    @Query("SELECT * FROM Region")
    suspend fun getRegionList(): List<Region>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertZone(country: List<Zone>)

    @Query("SELECT * FROM Zone")
    suspend fun getZoneList(): List<Zone>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArea(country: List<Area>)

    @Query("SELECT * FROM Area")
    suspend fun getAreaList(): List<Area>


}