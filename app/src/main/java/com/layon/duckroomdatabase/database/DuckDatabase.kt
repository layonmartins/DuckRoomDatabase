package com.layon.duckroomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.layon.duckroomdatabase.model.Duck
import com.layon.duckroomdatabase.model.tableName

@Database(entities = [Duck::class], version = 1, exportSchema = false)
abstract class DuckDatabase :  RoomDatabase() {

    abstract fun duckDao() : DuckDao

    companion object {

        @Volatile
        private var INSTANCE: DuckDatabase? = null

        fun getDatabase(context: Context) :  DuckDatabase {
            val tempInstance = INSTANCE
            //tempInstance?.let { return it }
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DuckDatabase::class.java,
                    "duck_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}