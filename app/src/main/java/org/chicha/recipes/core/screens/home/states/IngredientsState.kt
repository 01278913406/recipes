package org.chicha.recipes.core.screens.home.states

import org.chicha.recipes.domain.model.Ingredient

data class IngredientsState(
    var isLoading: Boolean = false,
    var data: List<Ingredient>? = null,
    var error: String = ""
)
