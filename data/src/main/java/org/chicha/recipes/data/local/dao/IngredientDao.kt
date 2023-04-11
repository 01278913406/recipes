package org.chicha.recipes.data.local.dao

import androidx.room.*
import org.chicha.recipes.data.local.entity.IngredientEntity

@Dao
interface IngredientDao {
    @Upsert
    suspend fun upsertIngredients(ingredients: List<IngredientEntity>)

    @Query("SELECT * FROM ingrediententity LIMIT 25")
    suspend fun getIngredients(): List<IngredientEntity>
}