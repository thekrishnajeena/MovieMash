package com.krishnajeena.moviemash.data

import com.krishnajeena.moviemash.network.ApiService
import io.reactivex.rxjava3.core.Single

interface MovieMashRepository{
    fun getMovies(): Single<MovieResponse>
    fun getTVShows(): Single<TVShowResponse>
    fun fetchMoviesAndTVShows(): Single<Pair<MovieResponse, TVShowResponse>>

    fun getReleases(): Single<ReleaseSuccessResponse>

    fun getTypeData(): Single<ReleaseSuccessResponse>

    fun getTypeDataCombined(): Single<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>

    fun getDetails(titleId: String): Single<Details>
}