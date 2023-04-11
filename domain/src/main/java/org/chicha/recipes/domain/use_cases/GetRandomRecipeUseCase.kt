package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Recipe
import org.chicha.recipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(): Flow<Resource<Recipe>>{
        return repository.getRandomMeal()
    }
}