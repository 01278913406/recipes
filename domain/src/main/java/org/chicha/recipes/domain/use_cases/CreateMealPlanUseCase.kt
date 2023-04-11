package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.MealPlan
import org.chicha.recipes.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateMealPlanUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(mealPlans: List<MealPlan>): Flow<Resource<String>>{
        return repository.createMealPlan(mealPlans)
    }
}