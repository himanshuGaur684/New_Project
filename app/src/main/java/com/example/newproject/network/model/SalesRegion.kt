package com.example.newproject.network.model

data class SalesRegion(
    val count_unsigned_contracts: Int,
    val last_month_sales: Double,
    val lmsd_weighted_units: Double,
    val mtd_new_selling_agents: Int,
    val mtd_unit_sales: Int,
    val mtd_weighted_units: Double,
    val region: String,
    val signed_contracts: Int,
    val territory: String
)