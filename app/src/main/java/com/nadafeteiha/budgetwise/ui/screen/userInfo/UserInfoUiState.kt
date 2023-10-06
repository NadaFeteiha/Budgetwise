package com.nadafeteiha.budgetwise.ui.screen.userInfo

import com.nadafeteiha.budgetwise.data.entity.UserInfo

data class UserInfoUiState(
    val name: String = "",
    val budget: Double = 0.0
)


fun UserInfoUiState.toEntity() = UserInfo(
    name = name,
    budget = budget
)
