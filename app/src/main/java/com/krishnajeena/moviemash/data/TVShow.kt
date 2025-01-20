package com.krishnajeena.moviemash.data


data class TVShowResponse(
    val titles: List<TVShow>
)

data class TVShow(
   val id: Int,
    val title: String,
    val year: Int,
    val imdb_Id: String,
    val tmdb_Id: Int,
    val tmdb_Type: String,
    val type: String
)

