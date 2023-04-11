package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.domain.model.MealPlan
import org.chicha.recipes.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealPlanByDayUseCase @Inject constructor(private val repository: MealPlanRepository) {
    operator fun invoke(strDayOfWeek: String): Flow<List<MealPlan>> {
        return repository.getMealPlansByDayOfTheWeek(strDayOfWeek)
    }
}