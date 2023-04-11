package org.chicha.recipes.domain.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRandomMeal(): Flow<Resource<Recipe>>
    fun getRecipeByMealId(mealId: String): Flow<Resource<Recipe>>
    suspend fun getAllRecipes(): List<Recipe>
    fun searchRecipe(searchString: String): Flow<Resource<List<Recipe>>>
}