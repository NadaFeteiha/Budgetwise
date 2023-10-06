package com.nadafeteiha.budgetwise.data.repository

import com.nadafeteiha.budgetwise.data.entity.Category
import com.nadafeteiha.budgetwise.data.local.dao.BudgetDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IRepository {
    suspend fun addCategory(category: Category)
    suspend fun updateSpendCategoryValue(categoryId: Long, spent: Double)
    suspend fun getTotalSpent(): Flow<Double>
    suspend fun getTotalBudget(): Double
    suspend fun getAllCategories(): Flow<List<Category>>
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

    override suspend fun getTotalBudget(): Double {
        return 2000.0
    }

    override suspend fun getAllCategories(): Flow<List<Category>> {
        return budgetDao.getCategories()
    }


}
