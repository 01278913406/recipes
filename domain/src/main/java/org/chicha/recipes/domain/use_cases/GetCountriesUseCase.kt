package org.chicha.recipes.domain.use_cases

import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.domain.model.Country
import org.chicha.recipes.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(private val repository: CountryRepository) {
    operator fun invoke(): Flow<Resource<List<Country>>> {

        return repository.getCountries()
    }
}