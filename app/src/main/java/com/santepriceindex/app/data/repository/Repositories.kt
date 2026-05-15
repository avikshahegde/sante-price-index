package com.santepriceindex.app.data.repository

import com.santepriceindex.app.data.model.MandiPrice
import com.santepriceindex.app.data.model.TrendEntry
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlin.random.Random

interface PriceRepository {
    fun getMandiPrices(): Flow<List<MandiPrice>>
}

interface TrendRepository {
    fun getTrends(crop: String): Flow<List<TrendEntry>>
}

class MockPriceRepository : PriceRepository {

    private val basePrices = listOf(
        MandiPrice("1", "Tomato", 2400.0, "Quintal", "Azadpur"),
        MandiPrice("2", "Potato", 1200.0, "Quintal", "Ghazipur"),
        MandiPrice("3", "Onion", 1800.0, "Quintal", "Okhla"),
        MandiPrice("4", "Carrot", 3200.0, "Quintal", "Azadpur"),
        MandiPrice("5", "Spinach", 1500.0, "Quintal", "Ghazipur"),
        MandiPrice("6", "Chilli", 4500.0, "Quintal", "Azadpur"),
        MandiPrice("7", "Ginger", 8000.0, "Quintal", "Okhla")
    )

    override fun getMandiPrices(): Flow<List<MandiPrice>> = flow {
        var currentPrices = basePrices

        while (currentCoroutineContext().isActive) {

            emit(currentPrices)

            delay(5000)

            currentPrices = currentPrices.map {

                val fluctuation = Random.nextDouble(-50.0, 50.0)

                it.copy(
                    price = (it.price + fluctuation)
                        .coerceAtLeast(100.0)
                )
            }
        }
    }
}

class MockTrendRepository : TrendRepository {

    override fun getTrends(crop: String): Flow<List<TrendEntry>> = flow {

        delay(1000)

        emit(
            listOf(
                TrendEntry("1", crop, 1800.0, "Mon"),
                TrendEntry("2", crop, 1950.0, "Tue"),
                TrendEntry("3", crop, 2100.0, "Wed"),
                TrendEntry("4", crop, 2050.0, "Thu"),
                TrendEntry("5", crop, 2400.0, "Fri"),
                TrendEntry("6", crop, 2300.0, "Sat"),
                TrendEntry("7", crop, 2500.0, "Sun")
            )
        )
    }
}