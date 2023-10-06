package com.nadafeteiha.budgetwise.ui.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.budgetwise.data.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: IRepository) : ViewModel(),
    CategoryInteractions {

    private val _uiState = MutableStateFlow(CategoryUIState())
    val uiState = _uiState.asStateFlow()
    override fun onColorSelected(color: Long) {
        _uiState.update { it.copy(selectedColor = color) }
    }

    override fun onIconSelected(icon: Int) {
        _uiState.update { it.copy(selectedIcon = icon) }
    }

    override fun onTitleChanged(title: String) {
        _uiState.update { it.copy(name = title) }

    }

    override fun onBudgetChanged(budget: Double) {
        _uiState.update { it.copy(total = budget) }
    }

    override fun onSaveClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCategory(_uiState.value.toEntity())
            _uiState.update { it.copy(isSuccessfullySaved = true) }
        }
    }
}
