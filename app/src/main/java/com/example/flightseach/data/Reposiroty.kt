package com.example.flightseach.data

import com.example.flightseach.data.dao.airportzDao
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.flow.Flow

interface Reposiroty{
    fun  getAllLIst(string: String):Flow<List<Airport>>
}

class offllineDataRepository(
    private val airportzDao: airportzDao
) :Reposiroty{
    override fun getAllLIst(string: String): Flow<List<Airport>> =airportzDao.getAllList(string)
}