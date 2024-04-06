package com.example.flightseach.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightseach.FlightAppliction
import com.example.flightseach.data.Reposiroty
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class mainCalss(
    val lsit:List<Airport>  = listOf()
)

class FlightViewModel(
    private val  reposiroty: Reposiroty
):ViewModel() {

    private var _SerarchText =  MutableStateFlow("")
    var SearchText = _SerarchText.asStateFlow()

    val suggetion : StateFlow<List<Airport>> =_SerarchText.flatMapLatest {
        reposiroty.getAllLIst(SearchText.value).catch { emit(emptyList()) }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    fun onValueCHange(string: String){
        _SerarchText.value = string
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