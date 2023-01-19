package com.example.newproject.network.api

import com.example.newproject.network.model.ApiResponse
import retrofit2.http.GET


interface ApiService {

    @GET("/v3/286f38b4-c6c1-4348-aabc-6d396dcbc4de")
    suspend fun getApiResponse():ApiResponse

}