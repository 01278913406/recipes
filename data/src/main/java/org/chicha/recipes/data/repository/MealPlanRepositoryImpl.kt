package org.chicha.recipes.data.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.data.local.dao.MealPlanDao
import org.chicha.recipes.data.local.entity.MealPlanEntity
import org.chicha.recipes.domain.model.MealPlan
import org.chicha.recipes.domain.repository.MealPlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(private val dao: MealPlanDao) :
    MealPlanRepository {
    override fun createMealPlan(mealPlans: List<MealPlan>): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val mealPlanEntities = mealPlans.map { mealPlan ->
                MealPlanEntity(
                    strDayOfWeek = mealPlan.strDayOfWeek,
                    strMealId = mealPlan.strMealId,
                    strMealThumbnail = mealPlan.strMealThumbnail, strMeaL = mealPlan.strMeal
                )
            }
            dao.insertMealPlans(mealPlanEntities)
            emit(Resource.Success(data = "Plan created successfully"))
        } catch (ex: Exception) {
            val error = ex.localizedMessage ?: "Unknown error occurred"
            emit(Resource.Error(error))
        }

    }

    override fun getMealPlans(): Flow<List<MealPlan>> =
        dao.getMealPlans().map { response -> response.map { it.toDomain() } }


    override fun getMealPlansByDayOfTheWeek(strDayOfWeek: String): Flow<List<MealPlan>> =
        dao.getMealPlanByDayOfTheWeek(strDayOfWeek)
            .map { response -> response.map { it.toDomain() } }

    override fun deleteMealPlanById(planId: Long): Flow<Resource<String>> = flow {
        try {
            dao.deleteMealPlanById(planId)
            emit(Resource.Success(data = "Plan Deleted"))
        } catch (ex: Exception) {
            val error = ex.localizedMessage ?: "Unknown error occurred"
            emit(Resource.Error(error))
        }
    }

}