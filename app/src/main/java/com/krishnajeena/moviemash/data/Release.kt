package com.krishnajeena.moviemash.data

data class ReleaseSuccessResponse(
    val titles: List<Release>
){
    data class Release(
//        val id: Int,
//        val title: String,
//        val type: String,
//        val imdb_id: String?,
//        val tmdb_id: Int?,
//        val tmdb_type: String?,
//        val season_number: Int?,
//        val poster_url: String?,
//        val source_release_date: String?,
//        val source_id: Int?,
//        val source_name: String?,
//        val is_original: Int?
        val id: Int,
        val title: String,
        val year: Int,
        val imdb_Id: String?,
        val tmdb_Id: Int?,
        val tmdb_Type: String?,
        val type: String
    )
}