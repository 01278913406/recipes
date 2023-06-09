package org.chicha.recipes.data.remote.dto


import org.chicha.recipes.data.local.entity.MealCategoryEntity
import com.squareup.moshi.Json

data class CategoryDTO(
    @Json(name = "idCategory")
    val idCategory: String?,
    @Json(name = "strCategory")
    val strCategory: String?,
    @Json(name = "strCategoryDescription")
    val strCategoryDescription: String?,
    @Json(name = "strCategoryThumb")
    val strCategoryThumb: String?
) {
    fun toEntity(): MealCategoryEntity {
        return MealCategoryEntity(
            idCategory = idCategory ?: "",
            strCategory = strCategory ?: "",
            strCategoryDescription = strCategoryDescription ?:"",
            strCategoryThumb = strCategoryThumb ?:""
        )
    }
}