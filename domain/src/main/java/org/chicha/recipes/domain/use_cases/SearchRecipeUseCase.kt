package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Recipe
import org.chicha.recipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(searString: String): Flow<Resource<List<Recipe>>>{
        return repository.searchRecipe(searString)
    }
}