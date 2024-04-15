package com.example.flightseach.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface airportzDao {
    @Query("""SELECT * FROM airport 
WHERE name LIKE '%' || :string || '%' or iata_code
 like '%'|| :string || '%'""" )
    fun getAllList(string: String):Flow<List<Airport>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun FavoriteInsert(favorite: Favorite)

    @Query("select * from favorite")
    fun getFavoritsList():Flow<List<Favorite>>
}