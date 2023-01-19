package com.example.newproject.network.model

data class ApiResponse(
    val ResponseData: ResponseData,
    val ResponseStatus: Int,
    val Success: Boolean
)