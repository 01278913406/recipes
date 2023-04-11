package org.chicha.recipes.domain.model

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val boolIsFavourite: Boolean = false,
)
