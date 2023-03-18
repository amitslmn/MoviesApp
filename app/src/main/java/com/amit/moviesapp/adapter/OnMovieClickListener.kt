package com.amit.moviesapp.adapter

interface OnMovieClickListener {
    fun onMovieClicked(id:Int)
    fun getPosterPath(posterPath: String)
    fun getMovieTitle(movieTitle: String)
    fun getMovieOriginalLanguage(originalLanguage: String)
    fun getMoviePopularity(popularity: Double)
    fun getMovieOverview(overview: String)
    fun getMovieVoteAverage(voteAverage: Double)
    fun getMovieVoteCount(voteCount: Int)
    fun getMovieReleaseDate(releaseDate: String)

}