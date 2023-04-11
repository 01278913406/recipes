package org.chicha.recipes.core.screens.recipe.components

import org.chicha.recipes.core.R
import org.chicha.recipes.core.screens.utils.Constants.INGREDIENTS
import org.chicha.recipes.core.screens.utils.Constants.INSTRUCTIONS

sealed class MealTabs(var icon: Int, var title: String) {
    object Ingredients : MealTabs(R.drawable.ic_terms_service, INGREDIENTS)
    object Instructions : MealTabs(R.drawable.ic_notes, INSTRUCTIONS)
}