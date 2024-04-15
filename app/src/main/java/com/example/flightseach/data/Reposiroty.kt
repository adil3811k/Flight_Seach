package com.example.flightseach.data

import com.example.flightseach.data.dao.airportzDao
import com.example.flightseach.data.table.Airport
import com.example.flightseach.data.table.Favorite
import kotlinx.coroutines.flow.Flow

interface Reposiroty{
    fun  getAllLIst(string: String):Flow<List<Airport>>
      suspend fun InserFavorait(favorite: Favorite)

      fun getFavoriteList():Flow<List<Favorite>>
}

class offllineDataRepository(
    private val airportzDao: airportzDao,
) :Reposiroty{
    override fun getAllLIst(string: String): Flow<List<Airport>> =airportzDao.getAllList(string)

    override suspend fun InserFavorait(favorite: Favorite) = airportzDao.FavoriteInsert(favorite)

    override fun getFavoriteList(): Flow<List<Favorite>> = airportzDao.getFavoritsList()
}
