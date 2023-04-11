package org.chicha.recipes.data.remote.dto


import org.chicha.recipes.data.local.entity.CountryEntity
import org.chicha.recipes.data.remote.utils.getFlagUrl
import com.squareup.moshi.Json

data class CountryDTO(
    @Json(name = "strArea")
    val strArea: String
){
    fun toCountryEntity(): CountryEntity{
        return CountryEntity(
            strArea = strArea,
            flagUrl = strArea.getFlagUrl()
        )
    }
}
