package com.nadafeteiha.budgetwise.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "INFO_TABLE")
data class UserInfo(
    @PrimaryKey(autoGenerate = false) val id: Long = 0,
    val name: String,
    val budget: Double,
)
