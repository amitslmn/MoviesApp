package com.amit.moviesapp.searchMovies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
// this is the model class of the search Movies response
class SearchResultResponse(
    //Movie Id
    @SerializedName("id")
    @Expose
    private var id: Int?,
    //Movie Name
    @SerializedName("title")
    @Expose
    private var title: String?,
    //Type
    @SerializedName("original_language")
    @Expose
    private var original_language: String?,
    //Movie Popularity
    @SerializedName("popularity")
    @Expose
    private var popularity: Double?,
    //OverView
    @SerializedName("overview")
    @Expose
    private var overview: String?,
    //average votes
    @SerializedName("vote_average")
    @Expose
    private var vote_average: Double?,
    //Total vote count
    @SerializedName("vote_count")
    @Expose
    private var vote_count: Int?,
    //Release Date
    @SerializedName("release_date")
    @Expose
    private var release_date: String?,
    //Image link
    @SerializedName("poster_path")
    @Expose
    private var poster_path: String?
) {

    fun getId(): Int? {
        return id
    }

    fun getPosterPath(): String {
        return "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/$poster_path"
    }

    fun getTitle():String?{
        return title
    }

    fun getOriginalLanguage(): String? {
        return original_language
    }

    fun getPopularity(): Double? {
        return popularity
    }

    fun getOverview(): String? {
        return overview
    }

    fun getVoteAverage(): Double? {
        return vote_average
    }

    fun getReleaseDate(): String? {
        return release_date
    }

    fun getVoteCount(): Int? {
        return vote_count
    }
}