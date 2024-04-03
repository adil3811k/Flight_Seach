package com.example.flightseach.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface airportzDao {
    @Query("SELECT * FROM airport WHERE name LIKE '%' || :string || '%' or iata_code like '%'|| :string || '%'" )
    fun getAllList(string: String):Flow<List<Airport>>
}