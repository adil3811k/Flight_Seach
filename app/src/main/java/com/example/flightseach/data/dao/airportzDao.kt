package com.example.flightseach.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface airportzDao {
    @Query("""SELECT * FROM airport 
WHERE name LIKE '%' || :string || '%' or iata_code
 like '%'|| :string || '%'""" )
    fun getSuggetion(string: String):Flow<List<Airport>>

    @Query("select * from airport")
    fun getAllAirport():Flow<List<Airport>>
    @Query("delete from favorite where departure_code= :Departuer and destination_code=:Destination")
    suspend fun deteteFavorite(Departuer:String,Destination:String)
    @Query("select * from airport where iata_code=:Iata_code limit 1")
    suspend fun getName(Iata_code:String):Airport
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun FavoriteInsert(favorite: Favorite)

    @Query("select * from favorite")
    fun getFavoritsList():Flow<List<Favorite>>
}