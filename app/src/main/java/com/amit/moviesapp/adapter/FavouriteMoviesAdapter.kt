package com.amit.moviesapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp22.R
import com.amit.moviesapp.room_database.Movies
import com.amit.moviesapp.room_database.MoviesDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavouriteMoviesAdapter(
    var context: Context
):RecyclerView.Adapter<FavouriteMoviesAdapter.MyViewHolder>() {
    private var movies = emptyList<Movies>()
    private var moviesDatabase = MoviesDatabase.getDatabase(context)
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val movieId:TextView = itemView.findViewById(R.id.movieId)
        val title:TextView = itemView.findViewById(R.id.movieTitle)
        val overview:TextView = itemView.findViewById(R.id.movieOverView)
        val originalLanguage:TextView = itemView.findViewById(R.id.movieOriginalLanguage)
        val voteCount:TextView = itemView.findViewById(R.id.movieTotalVote)
        val averageVote:TextView = itemView.findViewById(R.id.movieRatings)
        val releaseDate:TextView = itemView.findViewById(R.id.movieReleaseDate)
        val posterPath:ImageView = itemView.findViewById(R.id.movieImage)
        val popular:TextView = itemView.findViewById(R.id.moviePopularity)
        val removeFavourite:Button = itemView.findViewById(R.id.remove_favourite)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favourite_movies, parent, false))
    }

    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = movies[position]
        holder.movieId.text = "${holder.movieId.text}${currentItem.movieId}"
        holder.title.text = "${currentItem.moviesTitle}"
        holder.voteCount.text = "${holder.voteCount.text}${currentItem.moviesTotalVotes}"
        holder.averageVote.text = "${holder.averageVote.text}${currentItem.moviesRating}"
        holder.releaseDate.text = "${holder.releaseDate.text}${currentItem.moviesReleaseDate}"
        holder.popular.text = "${holder.popular.text}${currentItem.moviesPopularity}"
        holder.originalLanguage.text = "${holder.originalLanguage.text}${currentItem.moviesOriginalLanguage}"
        holder.overview.text = "${holder.overview.text}${currentItem.moviesOverview}"
        Picasso.get().load(currentItem.moviesPosterPath).into(holder.posterPath)
        holder.removeFavourite.setOnClickListener {
            GlobalScope.launch {
                removeFromFavourite(currentItem)
            }
            Toast.makeText(context,"movie Removed", Toast.LENGTH_LONG).show()
        }

    }
    override fun getItemCount(): Int {
        return movies.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(movies: List<Movies>){
        this.movies = movies
        notifyDataSetChanged()

    }
    @SuppressLint("NotifyDataSetChanged")
    private suspend fun removeFromFavourite(movies: Movies){
        moviesDatabase.moviesDao().delete(movies)

    }
}
