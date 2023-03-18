package com.amit.moviesapp.retrofit_api

import android.util.Log
import com.amit.moviesapp.searchActors.SearchActor
import com.amit.moviesapp.searchMovies.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitRequestManager {
    var path = HashMap<String,String>()
    // This is personal Api app key which is provided from the website
    var key:String = "97beb2a5b2a177aafe69d5ca504dd3d0"
    // Main URl to load the data from the website
    private val baseUri = "https://api.themoviedb.org/3/"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUri)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // this interface is written for the Variable factors in movies loading link
    interface getMovies {
        @GET("search/movie")
        fun callMovies(@Query("api_key") movie :String , @Query("query") query :String): Call<SearchResponse?>?
    }
    // this interface is written for the Variable factors in Actor loading link

    interface getActors{
        @GET("search/person")
        fun callActors(@Query("api_key") movie :String , @Query("query") query :String): Call<SearchActor?>?
    }
    // Retrofit Resoponse for the requested Actors
    fun SearchedActors(onSearchActorListener: OnSearchActorListener, actorName: String){
        val getActors = retrofit.create(getActors::class.java)
        val call: Call<SearchActor?>? = getActors.callActors(key,actorName)
        call?.enqueue(object : Callback<SearchActor?> {
            override fun onResponse(
                call: Call<SearchActor?>,
                response: Response<SearchActor?>
            ) {
                if (response.isSuccessful){
                    onSearchActorListener.OnResponse(response.body())
                }
            }
            override fun onFailure(call: Call<SearchActor?>, t: Throwable) {
                call.cancel()
                Log.d("TAG", t.stackTraceToString() + "Error")
                onSearchActorListener.OnError(t.message)
            }
        })


    }

    // Retrofit Resoponse for the requested Actors
    fun SearchedMovies(onSearchApiListener: OnSearchMovieListener, movieName: String) {
        val getMovies = retrofit.create(getMovies::class.java)

        val call: Call<SearchResponse?>? = getMovies.callMovies(key,movieName)
        call?.enqueue(object : Callback<SearchResponse?> {
            override fun onResponse(
                call: Call<SearchResponse?>,
                response: Response<SearchResponse?>
            ) {
                if (response.isSuccessful){
                    onSearchApiListener.OnResponse(response.body())
                }
            }
            override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                call.cancel()
                Log.d("TAG", t.stackTraceToString() + "Error")
                onSearchApiListener.OnError(t.message)
            }
        })
    }
    }

