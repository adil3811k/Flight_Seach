package com.example.flightseach.ui

import android.content.ClipData.Item
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightseach.data.table.Airport

@Composable
fun MainApp(
    viewModel: FlightViewModel = viewModel(factory = FlightViewModel.factory)
) {
    val searchText = viewModel.SerarchText.collectAsState()
    val List=  viewModel.getList(searchText.value).collectAsState()
    Column {
        SearchBar(text =searchText.value, onvaluechange ={viewModel.onvaluechange(it)}, onSerach = {})
        ListOfSuggetion(Suggetions = List.value.lsit)
    }

}



@Composable
fun SearchBar(
    text:String ,
    onvaluechange:(String)->Unit,
    onSerach:()->Unit
) {
    TextField(
        value = text,
        onValueChange = onvaluechange,
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

}




@Composable
fun ListOfSuggetion(
    Suggetions:List<Airport>
) {
    LazyColumn {
        items(Suggetions){it->
            SuggetionItem(airport = it)
        }
    }
}

@Composable
fun SuggetionItem(
    airport: Airport
) {
    Row(modifier = Modifier
        .padding(horizontal = 12.dp)
        .fillMaxWidth()) {
        Text(text = airport.iata_code, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.15f))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = airport.name, fontWeight = FontWeight.ExtraLight, modifier = Modifier.weight(0.9f))
    }
}