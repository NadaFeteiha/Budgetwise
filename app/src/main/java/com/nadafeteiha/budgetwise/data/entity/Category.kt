package com.nadafeteiha.budgetwise.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BUDGET_TABLE")
class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var total: Double,
    var color: Long,
    var icon: Int,
    var spent: Double = 0.0
)
