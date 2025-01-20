package com.krishnajeena.moviemash.data

data class ReleaseSuccessResponse(
    val titles: List<Release>
){
    data class Release(
       val id: Int,
        val title: String,
        val year: Int,
        val imdb_Id: String?,
        val tmdb_Id: Int?,
        val tmdb_Type: String?,
        val type: String
    )
}