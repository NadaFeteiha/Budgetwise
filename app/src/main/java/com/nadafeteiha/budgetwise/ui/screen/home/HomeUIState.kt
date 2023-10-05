package com.nadafeteiha.budgetwise.ui.screen.home


data class HomeUIState(
    val isLoading: Boolean = true,
    val error: String = "",
    val categories: List<CategoryUIState> = emptyList(),
    val totalSpent: Double = 0.0,
    val budget: Double = 0.0
)


data class CategoryUIState(
    val id: Int = 0,
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


//endregain
