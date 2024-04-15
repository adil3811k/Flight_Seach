package com.example.flightseach.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flightseach.data.table.Favorite

enum class Screens{
    First,
    Second
}

@Composable
fun MainApp() {
    val viewModel:FlightViewModel = viewModel(factory = FlightViewModel.factory)
    val navController = rememberNavController()
    val serachText by viewModel.SearchText.collectAsState()
    val AirportList by viewModel.suggetion.collectAsState()
    val FavoriteList by viewModel.FavoriteList.collectAsState()
    NavHost(navController = navController, startDestination = Screens.First.name) {
        composable(route=Screens.First.name){
            FirstScreen(
                searchText = serachText,
                onvaluechange =viewModel::onValueCHange,
                suggestions = AirportList
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
                SecondScreen(
                    string = AirportList[id].iata_code,
                    Destinations =AirportList.filter { it.id != id },
                    Departuer =AirportList[id-1],
                    favorite = FavoriteList,
                    onIconclick = {
                        viewModel.favoriteTroggle(Favorite(
                            destination_code = it.Destination.iata_code,
                            departure_code = it.Depature.iata_code
                                )
                            )
                        }
                    )
                Text(text = FavoriteList.toString())
                }
            }
        }
    }
