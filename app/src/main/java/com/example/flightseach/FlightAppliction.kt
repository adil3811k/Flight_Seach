package com.example.flightseach

import android.app.Application

class FlightAppliction  :Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}