package com.amit.moviesapp.searchMovies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// this is the model class of the search Movies

class SearchResponse(
    //Page
    @SerializedName("page")
    @Expose
    //total pages
    private var pages: Int? = 0,
    @SerializedName("total_pages")
    @Expose
    private var total_pages: Int? =0,
    // Total results
    @SerializedName("total_results")
    @Expose
    private var total_results: Int? = 0,
    @SerializedName("results")
    @Expose
    private var results:ArrayList<SearchResultResponse>? = null
)

{

    fun getPages(): Int? {
        return pages
    }
    fun setPages(pages: Int?) {
        this.pages = pages
    }
    fun getTotalPages(): Int? {
        return total_pages
    }
    fun setTotalPages(total_pages: Int?) {
        this.total_pages = total_pages
    }
    fun getTotalResults(): Int? {
        return total_results
    }
    fun setTotalResults(total_results: Int?) {
        this.total_results = total_results
    }
    fun setResults(results:ArrayList<SearchResultResponse>){
        this.results = results
    }
    fun getResults():ArrayList<SearchResultResponse>?{
        return results
    }

}