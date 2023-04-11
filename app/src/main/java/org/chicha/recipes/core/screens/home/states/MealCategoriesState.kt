package org.chicha.recipes.core.screens.home.states

import org.chicha.recipes.domain.model.MealCategory

data class MealCategoriesState(
    var isLoading: Boolean = false,
    var data: List<MealCategory>? = null,
    var error: String = ""
)