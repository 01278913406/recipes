package org.chicha.recipes.domain.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Meal
import kotlinx.coroutines.flow.Flow

interface MealRepository  {
    fun getMealByCategoryName(categoryName: String): Flow<Resource<List<Meal>>>
    fun getMealByIngredientName(ingredientName: String): Flow<Resource<List<Meal>>>
    fun getMealByCountryName(countryName: String): Flow<Resource<List<Meal>>>
    fun getaAllMeals(): Flow<List<Meal>>
    fun searchMeal(searchString: String): Flow<Resource<List<Meal>>>
    suspend fun toggleFavourite(isFavourite:Boolean, strMealId: String)
    fun getFavouriteMeals(): Flow<List<Meal>>
}