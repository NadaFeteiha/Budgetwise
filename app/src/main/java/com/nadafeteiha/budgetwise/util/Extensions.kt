package com.nadafeteiha.budgetwise.util

fun Double?.toStringOrEmpty(): String {
    return this?.toString() ?: ""
}

fun String.toDoubleOrZero(): Double {
    return if (this.isEmpty()) 0.0 else this.toDouble()
}
