package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.domain.model.Meal
import org.chicha.recipes.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritesUseCase @Inject constructor(private val repository: MealRepository) {
    operator fun invoke(): Flow<List<Meal>> {
        return repository.getFavouriteMeals()
    }
}