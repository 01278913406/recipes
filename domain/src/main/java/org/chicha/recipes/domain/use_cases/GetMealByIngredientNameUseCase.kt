package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Meal
import org.chicha.recipes.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealByIngredientNameUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(ingredientName: String): Flow<Resource<List<Meal>>>{
        return repository.getMealByIngredientName(ingredientName)
    }
}