package com.pdk.dicoding.kade.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdk.dicoding.kade.data.model.Event


/**
 * Created by Budi Ardianata on 01/10/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
@Database(entities = [Event::class], version = 1)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun footballDao(): FootballDao

    companion object {
        @Volatile
        private var INSTANCE: FootballDatabase? = null

        fun getDatabase(context: Context): FootballDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(FootballDatabase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FootballDatabase::class.java,
                    "database_github"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}