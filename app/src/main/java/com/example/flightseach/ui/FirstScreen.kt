package com.example.flightseach.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite
import kotlinx.coroutines.launch

@Composable
fun FirstScreen(
    searchText:String,
    onvaluechange:(String)->Unit,
    suggestions:List<Airport>,
    favorites:List<Flight>,
    onClick: (Int) -> Unit,
) {
    Column {
        TextField(
            value = searchText,
            onValueChange =onvaluechange,
            modifier = Modifier
                .height(100.dp)
                .padding(20.dp)
                .fillMaxWidth(),
            leadingIcon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icons"
            )},
            shape = RoundedCornerShape(50)
        )
        if(favorites.isEmpty() && searchText.isBlank()){
            Box (modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                Text(text = "There is not Favorites")
            }
        }else if (favorites.isNotEmpty() && searchText.isBlank()){
            LazyColumn {
                items(favorites){
                    FavoritesCard(flight = it)
                }
            }
        }
        else {
            LazyColumn {
                items(suggestions){
                    SuggetionItem(airport = it, onClick = onClick)
                }
            }
        }
    }

}


@Composable
fun SuggetionItem(
    airport: Airport,
    onClick:(Int)->Unit,
) {
    Row(modifier = Modifier
        .padding(horizontal = 12.dp)
        .fillMaxWidth()
        .clickable { onClick(airport.id) }) {
        Text(text = airport.iata_code, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.15f))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = airport.name, fontWeight = FontWeight.ExtraLight, modifier = Modifier.weight(0.9f))
    }
}
@Composable
fun FavoritesCard(
    flight: Flight,
    modifier: Modifier= Modifier,
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
        }
    }
}