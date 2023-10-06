package com.nadafeteiha.budgetwise.ui.screen.category

interface CategoryInteractions {
    fun onColorSelected(color: Long)

    fun onIconSelected(icon: Int)

    fun onTitleChanged(title: String)

    fun onBudgetChanged(budget: Double)

    fun onSaveClicked()
}
