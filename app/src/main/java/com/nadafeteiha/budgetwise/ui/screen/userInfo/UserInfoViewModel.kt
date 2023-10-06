package com.nadafeteiha.budgetwise.ui.screen.userInfo

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
class UserInfoViewModel @Inject constructor(private val repository: IRepository) : ViewModel(),
    UserInfoInteractions {

    private val _uiState = MutableStateFlow(UserInfoUiState())
    val uiState = _uiState.asStateFlow()
    override fun onNameValueChange(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    override fun onBudgetValueChange(budget: Double) {
        _uiState.update { it.copy(budget = budget) }

    }

    override fun onDoneClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUserInfo(_uiState.value.toEntity())
        }
    }


}
