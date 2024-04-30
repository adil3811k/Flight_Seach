package com.example.flightseach.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightseach.FlightAppliction
import com.example.flightseach.data.Reposiroty
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch






data class Flight(
    val Depature:Airport,
    val Destination:Airport,
    var isFavorite:Boolean= false
)




@OptIn(ExperimentalCoroutinesApi::class)
class FlightViewModel(
    private val  reposiroty: Reposiroty
):ViewModel() {
    private var _SerarchText =  MutableStateFlow("")
    var SearchText = _SerarchText.asStateFlow()
        private set
    val suggetion : StateFlow<List<Airport>> =_SerarchText.flatMapLatest {
        reposiroty.getSuggedtions(SearchText.value).catch { emit(emptyList()) }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    val FavoriteList  = reposiroty.getFavoriteList().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    private val  _FavoriteFlight= MutableStateFlow<List<Flight>>(emptyList())
    val FavoriteFlight = _FavoriteFlight.asStateFlow()
    val getAllAirport = reposiroty.getAllAirport().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    fun onValueCHange(string: String){
        _SerarchText.value = string
    }
    fun favoriteTroggle(favorite: Favorite,isFavorite: Boolean){
        if (isFavorite){
            viewModelScope.launch {
                reposiroty.deleteFavorite(favorite.departure_code,favorite.destination_code)
            }
        }else{
            viewModelScope.launch {
                reposiroty.InserFavorait(favorite)
            }
        }
    }
    fun getFavoriteFlight(){
        val result:MutableList<Flight>  = mutableListOf()
        viewModelScope.launch {
            FavoriteList.value.forEach {
                result.add(
                    Flight(
                        Airport(name = reposiroty.getName(it.departure_code), iata_code = it.departure_code),
                        Airport(name = reposiroty.getName(it.destination_code), iata_code = it.destination_code)
                    )
                )
            }
            _FavoriteFlight.update {
                result
            }
        }
    }


    companion object {
        val factory = viewModelFactory {
            initializer {
                val Application = (this[APPLICATION_KEY] as FlightAppliction)
                FlightViewModel(Application.container.reposiroty)
            }
        }
    }
}