package com.nadafeteiha.budgetwise.ui.screen.home

import com.nadafeteiha.budgetwise.data.entity.Category


data class HomeUIState(
    val isLoading: Boolean = true,
    val error: String = "",

    val date: String = "",
    val categories: List<CategoryUIState> = emptyList(),
    val totalSpent: Double = 0.0,
    val budget: Double = 0.0,

    val selectedCategoryId: Long? = null,
    val selectedSpendValue: Double = 0.0,
    val bottomSheetVisible: Boolean = false
)


data class CategoryUIState(
    val id: Long = 0,
    val icon: Int = 0,
    val title: String = "",
    val balance: Double = 0.0,
    val spent: Double = 0.0,
    val progress: Float = calculateProgress(spent, balance),
    val color: Long = 0L
)

private fun calculateProgress(spent: Double, total: Double): Float {
    require(total > 0) { "Total balance must be greater than 0" }
    return (spent / total).toFloat()
}

//regain Mapper
fun Category.toUiState() = CategoryUIState(
    id = id,
    icon = icon,
    color = color,
    spent = spent,
    title = title,
    balance = total
)

fun List<Category>.toUiState() = map { it.toUiState() }
//endregain
