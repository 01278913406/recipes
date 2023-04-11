package org.chicha.recipes.core.screens.addplan.states

import org.chicha.recipes.domain.model.Recipe

data class SearchRecipeState(
    var isLoading: Boolean = false,
    var data: List<Recipe>? = null,
    var error: String = ""
)
