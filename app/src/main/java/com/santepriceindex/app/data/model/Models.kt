package com.santepriceindex.app.data.model

data class MandiPrice(
    val id: String = "",
    val crop: String = "",
    val price: Double = 0.0,
    val unit: String = "Quintal",
    val market: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

data class TrendEntry(
    val id: String = "",
    val crop: String = "",
    val price: Double = 0.0,
    val date: String = ""
)
