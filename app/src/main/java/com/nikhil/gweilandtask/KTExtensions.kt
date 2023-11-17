package com.nikhil.gweilandtask

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double.formatAsPercentage(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
    formatter.applyPattern("¤#,##0.0")
    return if (this > 0) {
        "+" + formatter.format(this) + "%"
    } else {
        formatter.format(this) + "%"
    }
}