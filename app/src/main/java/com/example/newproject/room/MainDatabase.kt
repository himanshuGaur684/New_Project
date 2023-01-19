package com.example.newproject.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newproject.room.model.Area
import com.example.newproject.room.model.Country
import com.example.newproject.room.model.Region
import com.example.newproject.room.model.Zone

@Database(
    entities = [Country::class, Region::class, Zone::class, Area::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {


    companion object {
        fun getInstance(context: Context): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, "main_db").build()
        }
    }

    abstract fun getMainDao(): MainDAO

}