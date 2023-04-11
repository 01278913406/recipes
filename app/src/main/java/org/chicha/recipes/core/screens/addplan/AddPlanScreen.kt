package org.chicha.recipes.core.screens.addplan

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
import org.chicha.recipes.core.screens.addplan.components.AddMealSection
import org.chicha.recipes.core.screens.addplan.components.SelectDaySection
import org.chicha.recipes.core.screens.addplan.components.TopBarAddPlannerScreen
import org.chicha.recipes.core.screens.addplan.states.AddPlanState
import org.chicha.recipes.core.ui.theme.montserrat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlanScreen(
    navController: NavController,
    addPlanViewModel: AddPlanViewModel = hiltViewModel()
) {
    val openDialog = remember { mutableStateOf(false) }
    val createMealPlanResponse by addPlanViewModel.createMealPlanResponse.collectAsState(initial = AddPlanState())

    Scaffold(
        topBar = {
            TopBarAddPlannerScreen(navController)
        },
        content = { paddingValues ->
            if (createMealPlanResponse.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Loading")
                }
            } else if (createMealPlanResponse.error.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = createMealPlanResponse.error)
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
                        )
                ) {
                    SelectDaySection()
                    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                        Text(
                            text = "Click to Add Meal", style = TextStyle(
                                fontFamily = montserrat,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            )
                        )
                    }
                    AddMealSection(openDialog, createMealPlanResponse, navController)
                }
            }
        }
    )
    if (openDialog.value) {
        AddMealPlanDialog(openDialog = openDialog)
    }
}





