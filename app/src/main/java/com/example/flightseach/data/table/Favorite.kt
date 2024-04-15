package com.example.flightseach.data.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0 ,
    @ColumnInfo("departure_code")
    val departure_code:String,
    @ColumnInfo("destination_code")
    val destination_code:String
)
 