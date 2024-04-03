package com.example.flightseach.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flightseach.data.dao.airportzDao
import com.example.flightseach.data.table.Airport
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Airport::class], version = 1)
abstract class AppDataBase :RoomDatabase(){
    abstract fun FisrtDao():airportzDao

    companion object{
        @Volatile
         var Instence:AppDataBase  ?=null

        @OptIn(InternalCoroutinesApi::class)
        fun getDataBase(context: Context):AppDataBase{
            return Instence ?: synchronized(this){
                Room.databaseBuilder(context,AppDataBase::class.java,"AppDataBase")
                    .createFromAsset("DataBase/flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instence = it}
            }
        }
    }
}