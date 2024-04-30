package com.example.flightseach.data

import com.example.flightseach.data.dao.airportzDao
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite
import kotlinx.coroutines.flow.Flow

interface Reposiroty{
    fun  getSuggedtions(string: String):Flow<List<Airport>>
      suspend fun InserFavorait(favorite: Favorite)
    suspend fun deleteFavorite(Depatuer:String,Destination:String)
      fun getFavoriteList():Flow<List<Favorite>>
      suspend fun  getName(Iata_code:String):String
      fun getAllAirport():Flow<List<Airport>>
}

class offllineDataRepository(
    private val airportzDao: airportzDao,
) :Reposiroty{
    override fun getSuggedtions(string: String): Flow<List<Airport>> =airportzDao.getSuggetion(string)

    override suspend fun InserFavorait(favorite: Favorite) = airportzDao.FavoriteInsert(favorite)
    override suspend fun deleteFavorite(Depatuer: String, Destination: String) =airportzDao.deteteFavorite(Depatuer,Destination)
    override fun getFavoriteList(): Flow<List<Favorite>> = airportzDao.getFavoritsList()
    override suspend fun getName(Iata_code: String): String {
        val result = airportzDao.getName(Iata_code)
        return result.name
    }

    override fun getAllAirport(): Flow<List<Airport>> = airportzDao.getAllAirport()
}
