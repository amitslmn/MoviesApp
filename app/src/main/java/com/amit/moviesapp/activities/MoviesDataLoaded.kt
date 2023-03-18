package com.amit.moviesapp.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.moviesapp22.R
import com.amit.moviesapp.room_database.Movies
import com.amit.moviesapp.room_database.MoviesDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MoviesDataLoaded : AppCompatActivity() {
    private lateinit var moviesId:TextView
    private lateinit var overview:TextView
    private lateinit var popularity:TextView
    private lateinit var originalLanguage:TextView
    private lateinit var averageVote:TextView
    private lateinit var title:TextView
    private lateinit var voteCount:TextView
    private lateinit var releaseDate:TextView

    private lateinit var favourite:Button

    private  var id:String? = null
    private lateinit var moviesOverview :String
    private lateinit var moviesTitle:String
    private lateinit var moviesPopularity:String
    private lateinit var moviesAverageVote:String
    private lateinit var moviesVoteCount:String
    private lateinit var moviesOriginalLanguage:String
    private lateinit var moviesPosterPath:String
    private lateinit var moviesReleaseDate:String

    private lateinit var moviesDatabase: MoviesDatabase


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_data_loaded)

        favourite = findViewById(R.id.favourites)
        val image:ImageView = findViewById(R.id.movieImage)
        moviesId = findViewById(R.id.movieId)
        overview = findViewById(R.id.movieOverView)
         popularity= findViewById(R.id.moviePopularity)
         title= findViewById(R.id.movieTitle)
        originalLanguage = findViewById(R.id.movieOriginalLanguage)
        averageVote = findViewById(R.id.movieAverageVote)
        voteCount = findViewById(R.id.movieTotalVote)
        releaseDate = findViewById(R.id.movieReleaseDate)

        id = intent.getStringExtra("id").toString()
        moviesTitle = intent.getStringExtra("title").toString()
        moviesPopularity = intent.getStringExtra("popularity").toString()
        moviesOverview = intent.getStringExtra("overview").toString()
        moviesOriginalLanguage=intent.getStringExtra("original_language").toString()
        moviesAverageVote = intent.getStringExtra("voteAverage").toString()
        moviesVoteCount= intent.getStringExtra("voteCount").toString()
        moviesReleaseDate = intent.getStringExtra("release_date").toString()

        // get all the data from the main Activity
        moviesId.text = "${moviesId.text}$id"
        moviesPosterPath = intent.getStringExtra("posterPath").toString()
        title.text = moviesTitle
        popularity.text = "${popularity.text}$moviesPopularity"
        overview.text = "${overview.text}$moviesOverview"
        originalLanguage.text = "${originalLanguage.text}$moviesOriginalLanguage"
        averageVote.text = "${averageVote.text}$moviesAverageVote"
        voteCount.text = "${voteCount.text}$moviesVoteCount"
        releaseDate.text = "${releaseDate.text}$moviesReleaseDate"
        Picasso.get().load(moviesPosterPath).into(image)

        favourite.setOnClickListener {
            writeData()
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    private fun writeData(){
        if(id==null){
            Toast.makeText(this, "No data saved in data base", Toast.LENGTH_LONG).show()
        }
        else
        {
            // save the favourite movies in the data base

            moviesDatabase = MoviesDatabase.getDatabase(this@MoviesDataLoaded)
            val movies = Movies(id!!.toInt(),moviesPosterPath,moviesPopularity,moviesOriginalLanguage,
            moviesAverageVote, moviesReleaseDate, moviesVoteCount,moviesOverview, moviesTitle )
            GlobalScope.launch(Dispatchers.IO) {
            moviesDatabase.moviesDao().insert(movies)
            }
            Toast.makeText(this@MoviesDataLoaded, "Movie added to favourites", Toast.LENGTH_LONG).show()

        }

    }
}