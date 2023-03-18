package com.amit.moviesapp.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

// This is class for the creation of the movies table and its column name in the Database
@Entity(tableName = "movies_table")
data class Movies (
    @PrimaryKey(autoGenerate = true)
    val movieId:Int?,
    val moviesPosterPath:String?,
    val moviesPopularity:String?,
    val moviesOriginalLanguage:String?,
    val moviesRating:String?,
    val moviesReleaseDate:String?,
    val moviesTotalVotes:String?,
    val moviesOverview:String?,
    val moviesTitle:String?,
        )