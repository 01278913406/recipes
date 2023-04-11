package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Ingredient
import org.chicha.recipes.domain.repository.IngredientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(private val repository: IngredientRepository) {
    operator fun invoke(): Flow<Resource<List<Ingredient>>> {
        return repository.getIngredients()
    }
}