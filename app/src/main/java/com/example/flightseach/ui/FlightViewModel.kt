package com.example.flightseach.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightseach.FlightAppliction
import com.example.flightseach.data.Reposiroty
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class mainCalss(
    val lsit:List<Airport>  = listOf()
)

class FlightViewModel(
    private val  reposiroty: Reposiroty
):ViewModel() {

    val SerarchText =  MutableStateFlow("")
fun onvaluechange(Text:String){
    SerarchText.value = Text
}

      fun getList(string: String):StateFlow<mainCalss>{
         return  reposiroty.getAllLIst(string).map { mainCalss(it) }.stateIn(
             viewModelScope,
             SharingStarted.WhileSubscribed(),
             mainCalss()
         )
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