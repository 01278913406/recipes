package org.chicha.recipes.data.remote.dto


import com.squareup.moshi.Json

data class IngredientsResponse(
    @Json(name = "mealDTOS")
    val meals: List<IngredientDTO>?
)