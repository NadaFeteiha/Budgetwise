package com.nadafeteiha.budgetwise.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nadafeteiha.budgetwise.data.entity.Category
import com.nadafeteiha.budgetwise.data.entity.UserInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Category): Long

    @Delete
    suspend fun delete(item: Category)

    @Query("SELECT * FROM BUDGET_TABLE")
    fun getCategories(): Flow<List<Category>>

    @Query("UPDATE BUDGET_TABLE SET spent = spent + :spent WHERE id = :categoryId")
    suspend fun updateCategorySpendValue(categoryId: Long, spent: Double)

    @Query("SELECT SUM(spent) FROM BUDGET_TABLE")
    fun getTotalSpent(): Flow<Double>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInfo: UserInfo): Long

    @Query("SELECT * FROM INFO_TABLE LIMIT 1")
    fun getUser(): Flow<UserInfo?>
}
