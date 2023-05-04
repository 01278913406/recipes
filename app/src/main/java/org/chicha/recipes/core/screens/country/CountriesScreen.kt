package org.chicha.recipes.core.screens.country

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.chicha.recipes.core.R
import org.chicha.recipes.core.screens.country.components.CountriesSection
import org.chicha.recipes.core.screens.country.components.CountryMealsSection
import org.chicha.recipes.core.screens.country.components.SelectedCountry
import org.chicha.recipes.core.screens.home.HomeViewModel
import org.chicha.recipes.core.screens.home.states.MealsState
import org.chicha.recipes.core.screens.country.recipe.RecipeViewModels
import org.chicha.recipes.core.screens.country.recipe.states.RecipeState
import org.chicha.recipes.core.screens.utils.LottieAnime
import org.chicha.recipes.core.ui.theme.montserrat
import org.chicha.recipes.domain.model.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    navController: NavController,
    countriesViewModel: CountriesViewModel = hiltViewModel(),
    recipeViewModel: RecipeViewModels = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val countries = countriesViewModel.countries.value
    val meals by countriesViewModel.meals.collectAsState(initial = MealsState())
    val recipeState: MutableState<RecipeState?> = remember {
        mutableStateOf(null)
    }

    recipeState.value = recipeViewModel.recipe.collectAsState().value

    val selectedCountry: MutableState<Country?> = remember {
        mutableStateOf(null)
    }

    val savedCountryName = countriesViewModel.selectedCountryName.value
    Scaffold(
        content = { paddingValues ->
            if (selectedCountry.value == null) {
                if (countries.error.isEmpty() && !countries.isLoading) {
                    if (savedCountryName.isNotEmpty()) {
                        val tempCountry = countries.data?.find {
                            it.strArea == savedCountryName
                        }
                        selectedCountry.value = tempCountry
                    } else {
                        selectedCountry.value = countries.data?.getOrNull(0)
                    }
                }
            }
            if (selectedCountry.value == null) {
                Column(
                    modifier = Modifier
                        .padding(
                            bottom = paddingValues.calculateBottomPadding(),
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnime(
                        size = 100.dp,
                        lottieFile = R.raw.empty_list,
                        speed = 0.5f
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Check your internet connection.",
                        style = TextStyle(
                            fontFamily = montserrat,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    )

                }

            } else {
                Column(
                    modifier = Modifier
                        .padding(
                            bottom = paddingValues.calculateBottomPadding(),
                        )
                        .fillMaxSize()
                ) {
                    SelectedCountry(selectedCountry)
                    CountriesSection(countries, countriesViewModel, selectedCountry)
                    CountryMealsSection(meals, recipeViewModel, navController, homeViewModel)
                }
            }


        }
    )
}
