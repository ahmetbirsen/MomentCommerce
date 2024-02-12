package com.example.momentcommerce.util

import kotlin.math.pow
import kotlin.math.roundToInt

object MathUtils {

    fun roundDecimal(number: Double, decimalPlaces: Int): Double {
        val factor = 10.0.pow(decimalPlaces)
        return (number * factor).roundToInt() / factor
    }
}