package com.amit.moviesapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp22.R
import com.amit.moviesapp.adapter.*
import com.amit.moviesapp.retrofit_api.OnSearchActorListener
import com.amit.moviesapp.retrofit_api.OnSearchMovieListener
import com.amit.moviesapp.retrofit_api.RetrofitRequestManager
import com.amit.moviesapp.searchActors.SearchActor
import com.amit.moviesapp.searchMovies.SearchResponse

class MainActivity : AppCompatActivity(), OnMovieClickListener,OnActorClickedListener, AdapterView.OnItemSelectedListener {

    private lateinit var spinner: Spinner
    private lateinit var searchView:SearchView
    private lateinit var favourite:ImageView
    // Variables for movies data
    private var movieId:String = ""
    private  var originalLanguageM:String  = ""
    private var popularity:String = ""
    private var overview:String = ""
    private var voteAverage:String = ""
    private var voteCount: String = ""
    private var releaseDate:String = ""
    private var movieTitle:String = ""

    private var adult:String = "yes"
    private var gender:String = "Male"

    // variables for the actor data loaded from the Api
    private var actorId:Int = 0
    private var actorAdult:Boolean = true
    private var actorGender:Int = 0
    private var actorKnownForDepartment:String = ""
    private var actorName:String = ""
    private var actorOriginalName:String = ""
    private var actorPopularity:Float = 0.0f
    private var actorProfilePath:String = "null"

    private var position:Int = 0
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterClass: HomeAdapter

    private lateinit var searchActorAdapterClass: ActorAdapter

    // some movies and actor shows on the main screen at the start
    private lateinit var actor:String
    private lateinit var movie:String
    private val queries = listOf("ave", "x","Resi","Inc","Har")
    private val actors = listOf("Bra","Chri","Dwa","To","Wi")

    lateinit var txtData: TextView
    val searchResponseArray:ArrayList<SearchResponse> = ArrayList()
    // retrofit  Request manager
    val retrofitRequestManager = RetrofitRequestManager()
    var type = arrayOf("Search Movies","Search Actors")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtData = findViewById(R.id.textView1)
        spinner = findViewById(R.id.nice_spinner)
        favourite = findViewById(R.id.favouritesImage)

        spinner.onItemSelectedListener = this
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, type)
        spinner.adapter = adapter
        searchView = findViewById(R.id.etSearchMovies)
        recyclerView = findViewById(R.id.recyclerView)

        val random= (1..5).shuffled().last()
        // movies to randomly select when app starts
        movie = when(random){
            1-> queries[0]
            2-> queries[1]
            3-> queries[2]
            4-> queries[3]
            else -> queries[4]
        }
        // Actors to randomly select when app starts

        actor = when(random){
            1-> actors[0]
            2-> actors[1]
            3-> actors[2]
            4-> actors[3]
            else -> actors[4]
        }
    // search View response when text is input
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (type[position] =="Search Movies"){
                        retrofitRequestManager.SearchedMovies(listener, query)
                    }
                    else{
                        retrofitRequestManager.SearchedActors(listener2,query)
                    }
                }
                return true
            }

            @SuppressLint("SetTextI18n")
            override fun onQueryTextChange(newText: String?): Boolean {
                txtData.text = "searched for '$newText'"
                if (type[position] =="Search Movies"){
                    retrofitRequestManager.SearchedMovies(listener, newText!!)
                }
                else{
                    retrofitRequestManager.SearchedActors(listener2,newText!!)
                }
                return false
            }
        })

        // image button to check the favourite movies
        favourite.setOnClickListener {
            val intent = Intent(this, FavouriteMovies::class.java)
            startActivity(intent)
        }
        }

    //listener variable which override the OnSearchMoviesListener
    private val listener = object: OnSearchMovieListener {
        override fun OnResponse(searchResult: SearchResponse?) {
            if(searchResult==null){
                Toast.makeText(this@MainActivity, "Search result null" ,Toast.LENGTH_LONG).show()
                return
            }
            showResults(searchResult)
        }

        override fun OnError(message: String?) {
        Toast.makeText(this@MainActivity,"$message  can not load the data", Toast.LENGTH_LONG).show()
            txtData.text = message
        }


    }
    private val listener2 = object: OnSearchActorListener {
        override fun OnResponse(searchResult: SearchActor?) {
            if(searchResult==null){
                Toast.makeText(this@MainActivity, "Search result null" ,Toast.LENGTH_LONG).show()
                return
            }
            showResult(searchResult)
        }

        override fun OnError(message: String?) {
        Toast.makeText(this@MainActivity,"$message  can not load the data", Toast.LENGTH_LONG).show()
            txtData.text = message
        }


    }

    // this function is for showing the movies on the recyclerView
    private fun showResults(searchResponse: SearchResponse) {
        searchResponseArray.add(searchResponse)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapterClass = HomeAdapter(this, searchResponse.getResults()!!, this)
        recyclerView.adapter = adapterClass
    }
    // this function is for showing the actor on the recyclerView

    private fun showResult(searchActor: SearchActor) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
       searchActorAdapterClass = ActorAdapter(this, searchActor.getResults()!!, this)
        recyclerView.adapter = searchActorAdapterClass
    }
    // this function is called when we click on the movie it save the id of the movie clicked
    override fun onMovieClicked(id: Int) {
    movieId = "$id"
    }

    // this function is called when we click on the movie it save the Title of the movie clicked

    override fun getMovieTitle(movieTitle: String) {
        this.movieTitle = movieTitle
    }
    // this function is called when we click on the movie it save the Original language of the movie clicked

    override fun getMovieOriginalLanguage(originalLanguage: String) {
        this.originalLanguageM =originalLanguage
    }
    // this function is called when we click on the movie it save the Popularity of the movie clicked
    override fun getMoviePopularity(popularity: Double) {
        this.popularity = popularity.toString()
    }
    // this function is called when we click on the movie it save the overview of the movie clicked

    override fun getMovieOverview(overview: String) {
        this.overview = overview
    }
    // this function is called when we click on the movie it save the Average Votes of the movie clicked
    override fun getMovieVoteAverage(voteAverage: Double) {
       this.voteAverage = voteAverage.toString()
    }
    // this function is called when we click on the movie it save the Votes of the movie clicked
    override fun getMovieVoteCount(voteCount: Int) {
        this.voteCount = voteCount.toString()
    }
    // this function is called when we click on the movie it save the Release Date of the movie clicked
    override fun getMovieReleaseDate(releaseDate: String) {
     this.releaseDate = releaseDate
    }
    // this function is called when we click on the movie it save the poster Path of the movie clicked and also send the
    // data to another Activity "Movies Data Loader"
    override fun getPosterPath(posterPath: String) {
        val intent = Intent(this, MoviesDataLoaded::class.java)
        intent.putExtra("posterPath", posterPath)
        intent.putExtra("id", movieId)
        intent.putExtra("title", movieTitle)
        intent.putExtra("original_language", originalLanguageM)
        intent.putExtra("voteCount", voteCount)
        intent.putExtra("voteAverage", voteAverage)
        intent.putExtra("popularity", popularity)
        intent.putExtra("release_date", releaseDate)
        intent.putExtra("overview", overview)
        startActivity(intent)
    }

    // this is the overridden function of the spinner "Search movies/Search Actors"
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       this.position = position
        if (type[position] =="Search Movies"){
            retrofitRequestManager.SearchedMovies(listener, movie)
        }
        else{
            retrofitRequestManager.SearchedActors(listener2,actor)
        }
    }

    // this is the overridden function of the spinner "Search movies/Search Actors"
    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    // this function is called when we click on the actor it save the Name of the Actor clicked
    override fun getActorName(name: String) {
        this.actorName = name
    }
    // this function is called when we click on the actor it save the Actor Original Name of the Actor clicked
    override fun getActorOriginalName(originalName: String) {
        this.actorOriginalName = originalName
    }
    // this function is called when we click on the actor it save the Popularity of the Actor clicked
    override fun getActorPopularity(popularity: Float) {
      this.actorPopularity= popularity
    }
    // this function is called when we click on the actor it save the Acting Department of the actor of the movie Actor
    override fun getActorKnownForDepartment(knownForAdapter: String) {
       this.actorKnownForDepartment = knownForAdapter
    }
    // this function is called when we click on the actor it save the gender of the Actor clicked
    override fun getActorGender(gender: Int) {
        this.actorGender = gender
        if (gender==1){
            this.gender = "Male"
        }
    }
    // this function is called when we click on the actor it shows that the actor is adult or not.
    override fun getActorAdult(adult: Boolean) {
        this.actorAdult = adult
        if (!adult){
            this.adult = "No"
        }
    }
    // this function is called when we click on the actor it save the id of the Actor clicked
    override fun getActorId(id: Int) {
        this.actorId = id

    }
    // this function is called when we click on the actor it save the profile Path of the Actor clicked and also send the
    // data to another Activity "ActorDataLoader"
    override fun getProfilePath(profilePath: String) {
        this.actorProfilePath =profilePath
        val intent = Intent(this, ActorDataLoader::class.java)
        intent.putExtra("profilePath", actorProfilePath)
        intent.putExtra("id", actorId.toString())
        intent.putExtra("title", actorName)
        intent.putExtra("originalName", actorOriginalName)
        intent.putExtra("popularity", actorPopularity)
        intent.putExtra("knownForDepartment", actorPopularity)
        intent.putExtra("gender", gender )
        intent.putExtra("adult", adult)
        startActivity(intent)
    }


}
