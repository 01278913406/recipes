package org.chicha.recipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.chicha.recipes.data.local.dao.*
import org.chicha.recipes.data.local.entity.*

@Database(
    entities = [
        IngredientEntity::class,
        MealCategoryEntity::class,
        CountryEntity::class,
        MealEntity::class,
        RecipeEntity::class,
        MealPlanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CookBookDatabase : RoomDatabase() {
    abstract val ingredientDao: IngredientDao
    abstract val mealCategoryDao: MealCategoryDao
    abstract val countryDao: CountryDao
    abstract val mealDao: MealDao
    abstract val recipeDao: RecipeDao
    abstract val mealPlanDao: MealPlanDao
}