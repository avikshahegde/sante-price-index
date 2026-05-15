package com.santepriceindex.app.utils

import java.text.NumberFormat
import java.util.*

object PriceUtils {
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("en", "IN")).apply {
        maximumFractionDigits = 2
    }

    fun formatCurrency(amount: Double): String {
        return currencyFormatter.format(amount)
    }

    fun formatPricePerKg(amount: Double): String {
        return "₹${String.format("%.2f", amount)} / KG"
    }
}
