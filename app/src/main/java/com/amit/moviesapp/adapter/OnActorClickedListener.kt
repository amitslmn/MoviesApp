package com.amit.moviesapp.adapter

interface OnActorClickedListener {
    fun getActorId(id:Int)
    fun getProfilePath(profilePath: String)
    fun getActorName(name: String)
    fun getActorOriginalName(originalName: String)
    fun getActorPopularity(popularity: Float)
    fun getActorKnownForDepartment(knownForAdapter: String)
    fun getActorGender(gender: Int)
    fun getActorAdult(adult:Boolean )
}