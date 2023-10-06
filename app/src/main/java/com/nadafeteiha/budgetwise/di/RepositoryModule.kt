package com.nadafeteiha.budgetwise.di

import com.nadafeteiha.budgetwise.data.repository.IRepository
import com.nadafeteiha.budgetwise.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repository: Repository): IRepository
}
