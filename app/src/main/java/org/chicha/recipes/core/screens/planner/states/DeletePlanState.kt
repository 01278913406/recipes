package org.chicha.recipes.core.screens.planner.states

data class DeletePlanState(
    var isLoading: Boolean = false,
    var data: String? = null,
    var error: String = ""
)
