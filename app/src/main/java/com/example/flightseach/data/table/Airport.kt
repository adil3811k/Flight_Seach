package com.example.flightseach.data.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey
    val id:Int =0,
    @ColumnInfo("name")
    val name:String ="",
    @ColumnInfo("iata_code")
    val iata_code:String= "",
    @ColumnInfo("passengers")
    val passengers:Int =100
)
