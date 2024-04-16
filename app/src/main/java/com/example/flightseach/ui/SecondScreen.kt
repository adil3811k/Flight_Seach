package com.example.flightseach.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightseach.data.route
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite


@Composable
fun SecondScreen(
    string: String,
    Destinations: List<Airport>,
    Departuer: Airport,
    onIconclick: (Flight) -> Unit,
    favorite: List<Favorite>,
) {
    Log.d("SecondScreen","Second Screen compose")
    val  isFavorite = remember{ mutableStateOf(favorite) }
    Column {
        Text(
            text = "Flight form $string ",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(20.dp)
        )
        LazyColumn {
            items(GetFLight(Departuer,Destinations)){ flight->
                FlightCard(flight = flight , onIconclick = onIconclick, isfavrite = isFavorite(flight, favorite))
            }
        }
    }
}
private fun GetFLight(Departuer:Airport,Deatinations:List<Airport>):List<Flight>{
    var result:MutableList<Flight> = mutableListOf()
    Deatinations.forEach {
        result.add(Flight(Departuer,it))
    }


    return result
}




@Composable
fun FlightCard(
    flight: Flight,
    modifier: Modifier= Modifier,
    onIconclick:(Flight)->Unit,
    isfavrite:Boolean
) {
    Log.d("SecondScreen","Flight Card compose")
    Card (
        modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ){
        Row {
            val TextModifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp)
            Column (modifier.fillMaxWidth(0.8f)){
                Text(text = "DEPART",modifier =TextModifier.padding(top = 10.dp))
                Row {
                    Text(text = flight.Depature.iata_code,modifier =TextModifier, fontWeight = FontWeight.Bold)
                    Text(text = flight.Depature.name,modifier= TextModifier)
                }
                Text(text = "ARRIVE",modifier= TextModifier)
                Row {
                    Text(text = flight.Destination.iata_code, modifier = TextModifier, fontWeight = FontWeight.Bold)
                    Text(text = flight.Destination.name,modifier= TextModifier)
                }
            }
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favorite Stare",
                tint = if (isfavrite) Color.Yellow else Color.White,
                modifier= modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onIconclick(flight) }
                    .size(50.dp)
            )
        }
    }
}
private fun isFavorite(flight: Flight,favorite: List<Favorite>):Boolean {
    favorite.forEach {
        return flight.Destination.iata_code == it.destination_code && flight.Depature.iata_code == it.departure_code
    }
    return false
}
