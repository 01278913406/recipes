package org.chicha.recipes.data.remote.dto


import org.chicha.recipes.data.local.entity.MealEntity
import com.squareup.moshi.Json

data class MealDTO(
    @Json(name = "idMeal")
    val idMeal: String,
    @Json(name = "strMeal")
    val strMeal: String,
    @Json(name = "strMealThumb")
    val strMealThumb: String
){
    fun toMealEntity(): MealEntity {
        return MealEntity(
            idMeal = idMeal,
            strMeal = strMeal,
            strMealThumb = strMealThumb
        )
    }
}