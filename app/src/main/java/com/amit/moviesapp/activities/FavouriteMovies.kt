
package com.amit.moviesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp22.R
import com.amit.moviesapp.adapter.FavouriteMoviesAdapter
import com.amit.moviesapp.room_database.MoviesDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouriteMovies : AppCompatActivity() {
    private lateinit var moviesDatabase:MoviesDatabase
    private lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)
        moviesDatabase = MoviesDatabase.getDatabase(this)
         recyclerView = findViewById(R.id.recycler)
        loadData()

    }
     fun loadData(){

         // Load the all data from the Room DataBase

        GlobalScope.launch {
        val adapter = FavouriteMoviesAdapter(this@FavouriteMovies)
        recyclerView.layoutManager = LinearLayoutManager(this@FavouriteMovies)
        recyclerView.adapter = adapter
        val movies = moviesDatabase.moviesDao().getAllMovies()
        adapter.setData(movies)
        }
    }
}