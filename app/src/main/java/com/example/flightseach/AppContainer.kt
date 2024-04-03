package com.example.flightseach

import android.content.Context
import com.example.flightseach.data.AppDataBase
import com.example.flightseach.data.Reposiroty
import com.example.flightseach.data.offllineDataRepository

interface AppContainer {
    val reposiroty: Reposiroty
}
class AppDataContainer(context: Context):AppContainer{
    override val reposiroty: Reposiroty by lazy {
        offllineDataRepository(AppDataBase.getDataBase(context).FisrtDao())
    }
}