package com.example.flightseach.data

import com.example.flightseach.data.dao.airportzDao
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.flow.Flow

interface Reposiroty{
    fun  getAllLIst(string: String):Flow<List<Airport>>
     fun getDepartuer(string: String):Flow<Airport>

     fun getDestinations(string: String):Flow<List<Airport>>


}

class offllineDataRepository(
    private val airportzDao: airportzDao,
) :Reposiroty{
    override fun getAllLIst(string: String): Flow<List<Airport>> =airportzDao.getAllList(string)
    override  fun getDepartuer(string: String): Flow<Airport> = airportzDao.getDepatuer(string)
    override  fun getDestinations(string: String): Flow<List<Airport>> = airportzDao.getDestinatios(string)
}