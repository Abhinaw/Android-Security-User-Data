package com.psdemo.globomanticssales.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Client::class], version = 1, exportSchema = false)
abstract class ClientRoomDatabase : RoomDatabase() {
    abstract fun clientDao(): ClientDao

    companion object {
        @Volatile
        private var INSTANCE: ClientRoomDatabase? = null

        fun getInstance(context: Context): ClientRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClientRoomDatabase::class.java,
                    "client_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}