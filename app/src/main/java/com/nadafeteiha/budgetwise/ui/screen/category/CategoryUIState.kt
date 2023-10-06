package com.nadafeteiha.budgetwise.ui.screen.category

import com.nadafeteiha.budgetwise.R
import com.nadafeteiha.budgetwise.data.entity.Category

data class CategoryUIState(
    val name: String = "",
    val total: Double? = 0.0,

    val colors: List<Long> = getColors(),
    val selectedColor: Long? = null,

    val icons: List<Int> = getIcons(),
    val selectedIcon: Int? = null,

    val isSuccessfullySaved: Boolean = false
)


fun CategoryUIState.isAllDataSet(): Boolean {
    return name.isNotEmpty() && total != null && total > 0.0 && selectedColor != null && selectedIcon != null
}

private fun getColors(): List<Long> {
    return listOf(
        0xFFE57373, // Red
        0xFFFFD54F, // Amber
        0xFFFFA726, // Orange
        0xFFFF7043, // Deep Orange
        0xFF81C784, // Green
        0xFF4CAF50, // Light Green
        0xFF64B5F6, // Light Blue
        0xFF42A5F5, // Blue
        0xFF5C6BC0, // Indigo
        0xFF7E57C2  // Deep Purple
    )
}


private fun getIcons(): List<Int> {
    return listOf(
        R.drawable.shopping,
        R.drawable.education,
        R.drawable.fast_food_icon,
        R.drawable.food,
        R.drawable.medicine,
        R.drawable.transportation,
        R.drawable.travel,
    )
}


fun CategoryUIState.toEntity() = Category(
    title = name.trim(),
    total = total ?: 0.0,
    color = selectedColor ?: 0L,
    icon = selectedIcon ?: 0
)
