package org.chicha.recipes.domain.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.MealPlan
import kotlinx.coroutines.flow.Flow

interface MealPlanRepository {
    fun createMealPlan(mealPlans: List<MealPlan>): Flow<Resource<String>>
    fun getMealPlans(): Flow<List<MealPlan>>
    fun getMealPlansByDayOfTheWeek(strDayOfWeek: String): Flow<List<MealPlan>>
    fun deleteMealPlanById(planId: Long): Flow<Resource<String>>
}