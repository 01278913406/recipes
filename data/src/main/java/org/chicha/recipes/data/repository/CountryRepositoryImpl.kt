package org.chicha.recipes.data.repository

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.data.local.dao.CountryDao
import org.chicha.recipes.data.remote.CookPadApiService
import org.chicha.recipes.domain.model.Country
import org.chicha.recipes.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CookPadApiService,
    private val dao: CountryDao
) : CountryRepository {
    override fun getCountries(): Flow<Resource<List<Country>>> = flow {
        emit(Resource.Loading())
        val localCountries = dao.getCountries().map { it.toDomain() }
        emit(Resource.Loading(data = localCountries))
        try {
            val remoteCountries = api.getMealCountries().meals ?: emptyList()
            val newLocalCountries = remoteCountries.map {
                it.toCountryEntity()
            }
            dao.upsertCountries(newLocalCountries)
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Please check your internet connection",
                    data = localCountries
                )
            )
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = localCountries))
        }

        val newCountries = dao.getCountries().map { it.toDomain() }
        emit(Resource.Success(newCountries))
    }
}