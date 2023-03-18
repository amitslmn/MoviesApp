package com.amit.moviesapp.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movies::class], version = 1)
abstract class MoviesDatabase:RoomDatabase() {
    abstract  fun moviesDao(): MoviesDao
    // this is the companion object it run each time when database class is called
    companion object{
        @Volatile
        private var INSTANCE: MoviesDatabase? = null
        fun getDatabase(context: Context):MoviesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            // all the sensitive code written in the synchronized block. it allow processor it run only one
            // one task at a time. So the data base creation code is written in this block
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoviesDatabase::class.java,
                    "movies_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}