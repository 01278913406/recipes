package org.chicha.recipes.core.screens.planner.states

import org.chicha.recipes.domain.model.MealPlan

class MealPlansState(
    var isLoading: Boolean = false,
    var data: List<MealPlan>? = null,
    var error: String = ""
)