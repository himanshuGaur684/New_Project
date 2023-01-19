package com.example.newproject.network.model

data class SalesZone(
    val count_unsigned_contracts: Int,
    val last_month_sales: Int,
    val lmsd_weighted_units: Int,
    val mtd_new_selling_agents: Int,
    val mtd_unit_sales: Int,
    val mtd_weighted_units: Double,
    val signed_contracts: Int,
    val territory: String,
    val zone: String
)