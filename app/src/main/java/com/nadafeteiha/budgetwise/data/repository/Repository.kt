package com.nadafeteiha.budgetwise.data.repository

import com.nadafeteiha.budgetwise.data.entity.Category
import com.nadafeteiha.budgetwise.data.entity.UserInfo
import com.nadafeteiha.budgetwise.data.local.dao.BudgetDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IRepository {
    suspend fun addCategory(category: Category)
    suspend fun updateSpendCategoryValue(categoryId: Long, spent: Double)
    suspend fun getTotalSpent(): Flow<Double>
    suspend fun getTotalCategoriesBudget(): Flow<Double>
    suspend fun getAllCategories(): Flow<List<Category>>

    suspend fun addUserInfo(userInfo: UserInfo)
    suspend fun getUserInfo(): Flow<UserInfo?>
    suspend fun getUserBudget(): Double

}

class Repository @Inject constructor(private val budgetDao: BudgetDao) : IRepository {
    override suspend fun addCategory(category: Category) {
        budgetDao.insert(category)
    }

    override suspend fun updateSpendCategoryValue(categoryId: Long, spent: Double) {
        budgetDao.updateCategorySpendValue(categoryId, spent)
    }

    override suspend fun getTotalSpent(): Flow<Double> {
        return budgetDao.getTotalSpent()
    }

    override suspend fun getTotalCategoriesBudget(): Flow<Double> {
        return budgetDao.getTotalCategoriesBudget()
    }

    override suspend fun getAllCategories(): Flow<List<Category>> {
        return budgetDao.getCategories()
    }

    override suspend fun addUserInfo(userInfo: UserInfo) {
        budgetDao.insert(userInfo)
    }

    override suspend fun getUserInfo(): Flow<UserInfo?> {
        return budgetDao.getUser()
    }

    override suspend fun getUserBudget(): Double {
        return budgetDao.getUserBudget()
    }


}
