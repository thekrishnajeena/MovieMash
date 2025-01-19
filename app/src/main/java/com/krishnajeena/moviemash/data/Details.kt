package com.krishnajeena.moviemash.data

import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("plot_overview") val plotOverview: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("runtime_minutes") val runtimeMinutes: Int?,
    @SerializedName("year") val year: Int?,
    @SerializedName("end_year") val endYear: Int?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("tmdb_id") val tmdbId: Int?,
    @SerializedName("tmdb_type") val tmdbType: String?,
    @SerializedName("genres") val genres: List<Int>?,
    @SerializedName("genre_names") val genreNames: List<String>?,
    @SerializedName("user_rating") val userRating: Double?,
    @SerializedName("critic_score") val criticScore: Int?,
    @SerializedName("us_rating") val usRating: String?,
    @SerializedName("poster") val poster: String?,
    @SerializedName("backdrop") val backdrop: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("similar_titles") val similarTitles: List<Int>?,
    @SerializedName("networks") val networks: List<Int>?,
    @SerializedName("network_names") val networkNames: List<String>?,
    @SerializedName("trailer") val trailer: String?,
    @SerializedName("trailer_thumbnail") val trailerThumbnail: String?,
    @SerializedName("relevance_percentile") val relevancePercentile: Double?,
    @SerializedName("sources") val sources: List<Source>?
)

data class Source(
    @SerializedName("source_id") val sourceId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("region") val region: String,
    @SerializedName("ios_url") val iosUrl: String?,
    @SerializedName("android_url") val androidUrl: String?,
    @SerializedName("web_url") val webUrl: String,
    @SerializedName("format") val format: String,
    @SerializedName("price") val price: Double?,
    @SerializedName("seasons") val seasons: Int?,
    @SerializedName("episodes") val episodes: Int?
)

