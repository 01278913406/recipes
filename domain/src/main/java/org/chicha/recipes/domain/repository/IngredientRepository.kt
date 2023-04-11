package org.chicha.recipes.domain.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
     fun getIngredients(): Flow<Resource<List<Ingredient>>>
}