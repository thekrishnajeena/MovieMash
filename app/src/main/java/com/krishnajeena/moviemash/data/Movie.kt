package com.krishnajeena.moviemash.data

data class MovieResponse(
    val titles: List<Movie>
)
data class Movie(

    val id: Int,
    val title: String,
    val year: Int,
    val imdb_Id: String,
    val tmdb_Id: Int,
    val tmdb_Type: String,
    val type: String
)
