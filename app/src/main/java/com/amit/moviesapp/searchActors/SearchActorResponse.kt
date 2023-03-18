package com.amit.moviesapp.searchActors

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
// this is the model class of the search Actor Response
class SearchActorResponse(
    @SerializedName("adult")
    @Expose
    private var adult: Boolean?,
    @SerializedName("gender")
    @Expose
    private var gender: Int?,
    @SerializedName("id")
    @Expose
    private var id: Int?,
    @SerializedName("known_for_department")
    @Expose
    private var knownForDepartment: String?,
    @SerializedName("name")
    @Expose
    private var name: String?,
    @SerializedName("original_name")
    @Expose
    private var originalName: String?,
    @SerializedName("popularity")
    @Expose
    private var popularity: Float?,
    @SerializedName("profile_path")
    @Expose
    private var profilePath: String?
) {

    fun getAdult(): Boolean? {
        return adult
    }

    fun getGender(): Int? {
        return gender
    }

    fun getId(): Int? {
        return id
    }

    fun getKnownForDepartment(): String? {
        return knownForDepartment
    }

    fun getName(): String? {
        return name
    }

    fun getOriginalName(): String? {
        return originalName
    }

    fun getPopularity(): Float? {
        return popularity
    }

    fun getProfilePath(): String {
        return "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/$profilePath"
    }

}