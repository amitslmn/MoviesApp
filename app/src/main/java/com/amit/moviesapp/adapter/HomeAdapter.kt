package com.amit.moviesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp22.R
import com.amit.moviesapp.searchMovies.SearchResultResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeAdapter(
    var content: Context,
    var list: List<SearchResultResponse>,
    var movieClickListener: OnMovieClickListener
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(content).inflate(R.layout.card_view,parent,false)
        return ViewHolder(view)
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].getTitle()
      Picasso.get().load(
          list[position].getPosterPath()).into(holder.poster)
        holder.homeContainer.setOnClickListener {
            movieClickListener.onMovieClicked(list[position].getId()!!)
            movieClickListener.getMovieTitle(list[position].getTitle()!!)
            movieClickListener.getMovieOverview(list[position].getOverview()!!)
            movieClickListener.getMovieOriginalLanguage(list[position].getOriginalLanguage()!!)
            movieClickListener.getMovieVoteAverage(list[position].getVoteAverage()!!)
            movieClickListener.getMovieVoteCount(list[position].getVoteCount()!!)
            movieClickListener.getMoviePopularity(list[position].getPopularity()!!)
            movieClickListener.getMovieReleaseDate(list[position].getReleaseDate()!!)
            movieClickListener.getPosterPath(list[position].getPosterPath())

        }
        GlobalScope.launch {
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }

}
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var poster:ImageView = itemView.findViewById(R.id.imageView)
    var title:TextView = itemView.findViewById(R.id.actorName)
    var homeContainer:CardView = itemView.findViewById(R.id.homeContainer)

}