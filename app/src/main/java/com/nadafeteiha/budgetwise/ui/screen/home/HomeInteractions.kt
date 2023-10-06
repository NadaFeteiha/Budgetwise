package com.nadafeteiha.budgetwise.ui.screen.home

interface HomeInteractions {
    fun onCategorySelected(categoryId: Long)

    fun onSpentValueChange(spent: Double)

    fun onDoneClicked()

    fun onUpdateBottomSheetVisibility(isVisible: Boolean = false)

    fun onDialogDismiss()
}
