package com.nadafeteiha.budgetwise.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nadafeteiha.budgetwise.data.entity.Category
import com.nadafeteiha.budgetwise.data.local.dao.BudgetDao

@Database(entities = [Category::class], version = 1, exportSchema = false)
abstract class BudgetDataBase : RoomDatabase() {
    abstract fun budgetDao(): BudgetDao

}
