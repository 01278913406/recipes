package org.chicha.recipes.domain.model

data class MealPlan(
    val longId: Long? = 0,
    val strDayOfWeek: String,
    val boolIsFavourite: Boolean? = false,
    val strMealId: String,
    val strMeal: String,
    val strMealThumbnail: String
)
