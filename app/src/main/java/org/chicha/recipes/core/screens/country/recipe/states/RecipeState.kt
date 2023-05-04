package org.chicha.recipes.core.screens.country.recipe.states

import org.chicha.recipes.domain.model.Recipe

data class RecipeState(
    var isLoading: Boolean = false,
    var data: Recipe? = null,
    var error: String = ""
)