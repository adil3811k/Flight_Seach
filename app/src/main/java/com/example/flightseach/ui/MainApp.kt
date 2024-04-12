package com.example.flightseach.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.launch

enum class Screens{
    First,
    Second
}

@Composable
fun MainApp() {
    val viewModel:FlightViewModel = viewModel(factory = FlightViewModel.factory)
    val navController = rememberNavController()
    val serachText by viewModel.SearchText.collectAsState()
    val suggetion by viewModel.suggetion.collectAsState()
    NavHost(navController = navController, startDestination = Screens.First.name) {
        composable(route=Screens.First.name){
            FirstScreen(
                searchText = serachText,
                onvaluechange =viewModel::onValueCHange,
                suggestions = suggetion
            ){
                navController.navigate(Screens.Second.name+"/$it")
            }
        }
        composable(route= Screens.Second.name+"/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType .IntType
            }
        )){
            val id = it.arguments?.getInt("id")
            if (id != null) {
                ListShowScreen(
                    string = suggetion[id].iata_code,
                    Destinations =suggetion.filter { it.id  != id },
                    Departuer =suggetion[id-1]
                )
            }
        }
    }
}