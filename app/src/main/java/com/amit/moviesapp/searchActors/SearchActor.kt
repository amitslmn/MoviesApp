package com.amit.moviesapp.searchActors

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// this is the model class of the search Actor
class SearchActor(
    @SerializedName("results")
    @Expose
    private var results: ArrayList<SearchActorResponse>?) {
    fun getResults():ArrayList<SearchActorResponse>?{
        return results
    }
}