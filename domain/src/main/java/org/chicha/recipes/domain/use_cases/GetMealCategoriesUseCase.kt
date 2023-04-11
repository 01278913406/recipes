package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.MealCategory
import org.chicha.recipes.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealCategoriesUseCase @Inject constructor(private val repository: CategoryRepository) {
    operator fun invoke() : Flow<Resource<List<MealCategory>>> {
        return repository.getMealCategories()
    }
}