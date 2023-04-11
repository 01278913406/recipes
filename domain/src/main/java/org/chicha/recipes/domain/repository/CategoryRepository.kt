package org.chicha.recipes.domain.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.MealCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getMealCategories(): Flow<Resource<List<MealCategory>>>
    suspend fun getMealsByCategories()
}