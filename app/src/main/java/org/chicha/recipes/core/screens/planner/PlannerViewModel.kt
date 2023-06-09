package org.chicha.recipes.core.screens.planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.core.screens.planner.states.DeletePlanState
import org.chicha.recipes.core.screens.planner.states.MealPlansState
import org.chicha.recipes.core.screens.utils.getDay
import org.chicha.recipes.domain.use_cases.DeleteMealPlanUseCase
import org.chicha.recipes.domain.use_cases.GetMealPlanByDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlannerViewModel @Inject constructor(
    private val getMealPlanByDayUseCase: GetMealPlanByDayUseCase,
    private val deleteMealPlanUseCase: DeleteMealPlanUseCase
) : ViewModel() {

    private var _mealPlans = MutableStateFlow(MealPlansState())
    val mealPlans = _mealPlans.asStateFlow()

    private var _deleteMealPlanResponse = MutableStateFlow(DeletePlanState())
    val deleteMealPlanResponse = _deleteMealPlanResponse.asStateFlow()

    init {
        getMealPlansByDayOfTheWeek(getDay())
    }


    fun getMealPlansByDayOfTheWeek(strDayOfWeek: String) {
        getMealPlanByDayUseCase(strDayOfWeek).onEach { result ->
            _mealPlans.value = MealPlansState(data = result)
        }.launchIn(viewModelScope)
    }

    fun deleteMealPlan(planId: Long) {
        deleteMealPlanUseCase(planId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _deleteMealPlanResponse.value = DeletePlanState(isLoading = true)
                }

                is Resource.Success -> {
                    _deleteMealPlanResponse.value = DeletePlanState(data = result.data)
                }

                is Resource.Error -> {
                    _deleteMealPlanResponse.value = DeletePlanState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}