package com.anto.tkanekajaya.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AnekaJaya::class], version = 1, exportSchema = false)
abstract class AnekaJayaDatabase : RoomDatabase() {

    abstract val anekaJayaDAO: AnekaJayaDAO

    companion object {
        @Volatile
        private var INSTANCE: AnekaJayaDatabase? = null

        fun getInstance(context: Context) : AnekaJayaDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AnekaJayaDatabase::class.java,
                        "anekajaya_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}