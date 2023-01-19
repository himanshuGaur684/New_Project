package com.example.newproject.repository

import android.util.Log
import com.example.newproject.common.Resource
import com.example.newproject.network.api.ApiService
import com.example.newproject.network.model.ApiResponse
import com.example.newproject.room.MainDAO
import com.example.newproject.room.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(val apiService: ApiService, val dao: MainDAO) {
    suspend fun getApiResponse(): Resource<Unit> {
        return try {
            val response = apiService.getApiResponse()
            val country = response.ResponseData.sales_country.map { it.toDomainCountry() }
            val zone = response.ResponseData.sales_zone.map { it.toDomainZone() }
            val region = response.ResponseData.sales_region.map { it.toDomainRegion() }
            val area = response.ResponseData.sales_area.map { it.toDomainArea() }
            dao.insertCountry(country)
            dao.insertArea(area)
            dao.insertRegion(region)
            dao.insertZone(zone)
            Resource.Success(Unit)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    suspend fun getCountryList():List<Country> = dao.getCountryList()

    suspend fun getRegionList(teritory: String): List<Region> {
        val regionList = dao.getRegionList()
        val modifiedList = mutableListOf<Region>()
        regionList.forEach {
            if (it.territory.startsWith(teritory)) {
                modifiedList.add(it)
            }
        }
        return modifiedList
    }

    suspend fun getZoneList(s:String): List<Zone> {
        val list = s
        val zoneList = dao.getZoneList()
        val modifiedList = mutableListOf<Zone>()
        zoneList.forEach {
            if (it.territory.startsWith(list)) {
                modifiedList.add(it)
            }
        }
        return modifiedList
    }

    suspend fun getAreaList(teritory: String): List<Area> {
        val list = dao.getAreaList()
        val modifiedList = mutableListOf<Area>()
        list.forEach {
            if (it.territory.startsWith(teritory)) {
                modifiedList.add(it)
            }
        }
        return dao.getAreaList()
    }

}



