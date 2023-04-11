package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteMealPlanUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(planId:Long):Flow<Resource<String>>{
        return repository.deleteMealPlanById(planId)
    }
}