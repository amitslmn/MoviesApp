package com.amit.moviesapp.room_database

import androidx.room.*

//All the Queries will be written in the dao interface
@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies_table")
    fun getAllMovies():List<Movies>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movies: Movies)
    @Delete
    suspend fun delete(movies: Movies)

}