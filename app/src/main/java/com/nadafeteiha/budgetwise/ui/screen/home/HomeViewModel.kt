package com.nadafeteiha.budgetwise.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.budgetwise.data.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IRepository) : ViewModel(),
    HomeInteractions {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getCategories()
        calculateTotals()
        setDate()
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserInfo().collectLatest { user ->
                if (user == null) {
                    _uiState.update { it.copy(showDialog = true) }
                } else {
                    _uiState.update { it.copy(budget = user.budget, showDialog = false) }
                }
            }

        }
    }

    private fun calculateTotals() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTotalSpent().collectLatest { totalSpent ->
                _uiState.update { it.copy(totalSpent = totalSpent) }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCategories().collectLatest { categories ->
                _uiState.update { it.copy(categories = categories.toUiState()) }
            }
        }
    }

    private fun setDate() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        _uiState.update { it.copy(date = dateFormat.format(calendar.time)) }
    }

    override fun onCategorySelected(categoryId: Long) {
        _uiState.update { it.copy(selectedCategoryId = categoryId) }
    }

    override fun onSpentValueChange(spent: Double) {
        _uiState.update { it.copy(selectedSpendValue = spent) }
    }

    override fun onDoneClicked() {
        viewModelScope.launch {
            repository.updateSpendCategoryValue(
                _uiState.value.selectedCategoryId!!,
                _uiState.value.selectedSpendValue
            )
            _uiState.update {
                it.copy(
                    selectedSpendValue = 0.0, selectedCategoryId = null, bottomSheetVisible = false
                )
            }
        }
    }

    override fun onUpdateBottomSheetVisibility(isVisible: Boolean) {
        _uiState.update {
            it.copy(
                selectedSpendValue = 0.0,
                selectedCategoryId = null,
                bottomSheetVisible = isVisible
            )
        }

    }

    override fun onDialogDismiss() {
        _uiState.update { it.copy(showDialog = false) }
    }


}
