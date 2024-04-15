package com.example.flightseach.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightseach.data.table.Airport

@Composable
fun FirstScreen(
    searchText:String,
    onvaluechange:(String)->Unit,
    suggestions:List<Airport>,
    onClick: (Int) -> Unit
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
        LazyColumn {
            items(suggestions){
                SuggetionItem(airport = it, onClick = onClick)
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
        .clickable {onClick(airport.id)}) {
        Text(text = airport.iata_code, fontWeight = FontWeight.Bold, modifier = Modifier.weight(0.15f))
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = airport.name, fontWeight = FontWeight.ExtraLight, modifier = Modifier.weight(0.9f))
    }
}