package com.nadafeteiha.budgetwise.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadafeteiha.budgetwise.data.repository.IRepository
import com.nadafeteiha.budgetwise.ui.screen.userInfo.UserInfoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: IRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UserInfoUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserInfo().collectLatest { user ->
                user?.let { _uiState.update { it.copy(name = user.name, budget = user.budget) } }
            }

        }
    }

}
