package org.chicha.recipes.data.local.dao

import androidx.room.*
import org.chicha.recipes.data.local.entity.MealCategoryEntity

@Dao
interface MealCategoryDao {
    @Upsert
    suspend fun upsertMeals(categories: List<MealCategoryEntity>)

    @Query("SELECT * FROM mealCategoryEntity")
    suspend fun getMealCategories(): List<MealCategoryEntity>
}