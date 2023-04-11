package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.domain.repository.MealRepository
import javax.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(private val repository: MealRepository) {
    suspend operator fun invoke(isFavourite:Boolean, strMealId: String){
        repository.toggleFavourite(isFavourite, strMealId)
    }
}