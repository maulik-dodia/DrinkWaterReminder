package com.waterdrinkreminder.util

object DateTimeUtils {

    const val ddMMMyyyy = "dd/MMM/yyyy"

    fun checkForDoubleDigit(varToBeChecked: Int) = if (varToBeChecked < 10) "0$varToBeChecked" else varToBeChecked
}