package org.chicha.recipes.core.screens.home.states

import org.chicha.recipes.domain.model.Meal

data class MealsState(
    var isLoading: Boolean = false,
    var data: List<Meal>? = null,
    var error: String = ""
)