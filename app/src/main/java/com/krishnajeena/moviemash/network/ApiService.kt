package com.krishnajeena.moviemash.network

import com.krishnajeena.moviemash.data.Details
import com.krishnajeena.moviemash.data.MovieResponse
import com.krishnajeena.moviemash.data.ReleaseSuccessResponse
import com.krishnajeena.moviemash.data.TVShowResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/releases/")
    fun getMovies(
        @Query("apiKey") apiKey: String
       ): Single<MovieResponse>


    @GET("v1/releases/")
    fun getTVShows(
        @Query("apiKey") apiKey: String,
    ): Single<TVShowResponse>

    @GET("v1/releases/")
    fun getReleases(
        @Query("apiKey") apiKey: String,
    ) : Single<ReleaseSuccessResponse>

    @GET("v1/list-titles/")
    fun getTypeData(
        @Query("apiKey") apiKey: String,
        @Query("types") types: String
    ) : Single<ReleaseSuccessResponse>

    @GET("v1/title/{titleId}/details/")
    fun getDetails(
        @Path("titleId") titleId: String,
        @Query("apiKey") apiKey: String,
        @Query("append_to_response") appendToResponse: String
    ): Single<Details>

}