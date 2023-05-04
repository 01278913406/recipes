package org.chicha.recipes.core


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.datastore.dataStore
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.currentBackStackEntryAsState

import org.chicha.recipes.core.navigation.Route
import org.chicha.recipes.core.navigation.bottomNavigation.BottomNavigationBar
import org.chicha.recipes.core.screens.addplan.AddPlanScreen
import org.chicha.recipes.core.screens.category.CategoriesScreen
import org.chicha.recipes.core.screens.country.CountriesScreen
import org.chicha.recipes.core.screens.favourites.FavouritesScreen
import org.chicha.recipes.core.screens.home.HomeScreen
import org.chicha.recipes.core.screens.ingredient.IngredientsScreen
import org.chicha.recipes.core.screens.ingredient.SingleIngredientScreen
import org.chicha.recipes.core.screens.meals.MealsScreen
import org.chicha.recipes.core.screens.planner.PlannerScreen
import org.chicha.recipes.core.screens.country.recipe.RecipeScreen
import org.chicha.recipes.core.screens.utils.getActivity
import org.chicha.recipes.core.ui.theme.CookPadThem
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import org.chicha.recipes.core.adsconfig.BannerAdView

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookPadThem {
                /* MainScreen()*/

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colors.background
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        BannerAdView()
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val context = LocalContext.current
    val activity = context.getActivity()
    val window = activity?.window
    if (window != null) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    val windowInsetsController =
        window?.let { ViewCompat.getWindowInsetsController(it.decorView) }

    windowInsetsController?.isAppearanceLightNavigationBars = true

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberAnimatedNavController()
        var showBottomBar by rememberSaveable { mutableStateOf(false) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        showBottomBar = when (navBackStackEntry?.destination?.route) {
            Route.HomeScreen.route -> true
            Route.CategoriesScreen.route -> true
            Route.CountriesScreen.route -> true
            Route.PlannerScreen.route -> true
            Route.IngredientsScreen.route -> true
            Route.FavouritesScreen.route -> true
            else -> false
        }
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(navController = navController)
                }
            },
        ) {
            AnimatedNavHost(navController = navController, startDestination = "home_screen") {
                screens(navController)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.screens(navController: NavController) {
    composable(
        route = Route.HomeScreen.route,
        enterTransition = {
            slideInHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                initialOffsetX = {
                    -it
                }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -it
                }
            )
        },
    ) {
        HomeScreen(navController)
    }

    composable(route = Route.RecipeScreen.route + "/{meal_id}",
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(500),
                initialOffsetX = {
                    it
                }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(500),
                targetOffsetX = {
                    it
                }
            )
        }) {
        RecipeScreen(navController)
    }

    composable(route = Route.CategoriesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -it
                }
            )
        }) {
        CategoriesScreen(navController)
    }

    composable(route = Route.CountriesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = tween(1000))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(1000))
        }) {
        CountriesScreen(navController)
    }


    composable(route = Route.MealsScreen.route + "/{category_name}",
        enterTransition = {
            fadeIn(animationSpec = tween(3000))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(3000))
        }) {
        MealsScreen(navController)
    }

    composable(route = Route.IngredientsScreen.route) {
        IngredientsScreen(navController)
    }
    composable(route = Route.CategoriesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -it
                }
            )
        }) {
        CategoriesScreen(navController)
    }

    composable(route = Route.SingleIngredientsScreen.route + "/{ingredient_name}",
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                targetOffsetX = {
                    -it
                }
            )
        }) {
        SingleIngredientScreen(navController)
    }

    composable(route = Route.PlannerScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        }) {
        PlannerScreen(navController)
    }

    composable(route = Route.AddPlanScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        }) {
        AddPlanScreen(navController)
    }

    composable(route = Route.FavouritesScreen.route,
        enterTransition = {
            fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        },
        exitTransition = {
            fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
        }) {
        FavouritesScreen(navController)
    }
}