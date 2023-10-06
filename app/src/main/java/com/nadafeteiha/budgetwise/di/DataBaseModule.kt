package com.nadafeteiha.budgetwise.di

import android.content.Context
import androidx.room.Room
import com.nadafeteiha.budgetwise.data.local.BudgetDataBase
import com.nadafeteiha.budgetwise.data.local.dao.BudgetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): BudgetDataBase =
        Room.databaseBuilder(context, BudgetDataBase::class.java, "BudgetDatabase").build()

    @Singleton
    @Provides
    fun provideBudgetDao(budgetDB: BudgetDataBase): BudgetDao {
        return budgetDB.budgetDao()
    }
}
