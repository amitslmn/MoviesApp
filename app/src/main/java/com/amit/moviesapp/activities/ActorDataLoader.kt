package com.amit.moviesapp.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.moviesapp22.R
import com.squareup.picasso.Picasso

class ActorDataLoader : AppCompatActivity() {
    lateinit var actorId: TextView
    lateinit var actorTitle: TextView
    lateinit var popularity: TextView
    lateinit var knownForDepartment: TextView
    lateinit var profileImage: ImageView
    lateinit var adult: TextView
    lateinit var gender: TextView
    lateinit var originalName: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_data_loader)

        actorId = findViewById(R.id.actorId)
        gender = findViewById(R.id.actorGender)
        popularity= findViewById(R.id.actorPopularity)
        actorTitle= findViewById(R.id.actorName)
        adult = findViewById(R.id.actorAdult)
        knownForDepartment = findViewById(R.id.actorKnownForDepartment)
        originalName = findViewById(R.id.actorOrgionalName)
        profileImage = findViewById(R.id.actorImage)
        //get the actor data from main activity
        val intent = intent
        actorId.text = "${actorId.text}${intent.getStringExtra("id")}"
        actorTitle.text = intent.getStringExtra("title")
        popularity.text = "${popularity.text}${intent.getStringExtra("popularity")}"
        originalName.text = "${originalName.text}${intent.getStringExtra("originalName")}"
        gender.text = "${gender.text}${intent.getStringExtra("gender")}"
        adult.text = "${adult.text}${intent.getStringExtra("adult")}"
        knownForDepartment.text = "${knownForDepartment.text}${intent.getStringExtra("knownForDepartment")}"
        Picasso.get().load(intent.getStringExtra("profilePath")).into(profileImage)


    }
}