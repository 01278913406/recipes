package org.chicha.recipes.core.screens.recipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.chicha.recipes.common.util.Resource
import org.chicha.recipes.core.screens.recipe.states.RecipeState
import org.chicha.recipes.core.screens.utils.Constants.MEAL_ID
import org.chicha.recipes.domain.use_cases.GetRecipeByMealIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RecipeViewModels @Inject constructor(
    private val getRecipeByMealIdUseCase: GetRecipeByMealIdUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private var _recipe = MutableStateFlow(RecipeState())
    val recipe = _recipe.asStateFlow()

    init {
        savedStateHandle.get<String>(MEAL_ID)?.let { mealId ->
            getRecipeByMealId(mealId)
        }
    }
     fun getRecipeByMealId(mealId: String) {
         getRecipeByMealIdUseCase(mealId).onEach { result ->
             when (result) {
                 is Resource.Loading -> {
                     _recipe.value = RecipeState(isLoading = true)
                 }
                 is Resource.Success -> {
                     _recipe.value = RecipeState(data = result.data)
                 }
                 is Resource.Error -> {
                     _recipe.value = RecipeState(error = result.error.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
