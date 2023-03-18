package com.amit.moviesapp.retrofit_api

import com.amit.moviesapp.searchActors.SearchActor

interface OnSearchActorListener {
    fun OnResponse(searchResult: SearchActor?)
    fun OnError(message:String?)
}