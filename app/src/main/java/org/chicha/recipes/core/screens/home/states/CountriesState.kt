package org.chicha.recipes.core.screens.home.states

import org.chicha.recipes.domain.model.Country

data class CountriesState(
    var isLoading: Boolean = false,
    var data: List<Country>? = null,
    var error: String = ""
)