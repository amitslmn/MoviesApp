package com.amit.moviesapp.retrofit_api

import com.amit.moviesapp.searchMovies.SearchResponse

interface OnSearchMovieListener {
    fun OnResponse(searchResult: SearchResponse?)
    fun OnError(message:String?)

}