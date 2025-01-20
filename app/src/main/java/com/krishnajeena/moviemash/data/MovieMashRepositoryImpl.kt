package com.krishnajeena.moviemash.data

import com.krishnajeena.moviemash.BuildConfig
import com.krishnajeena.moviemash.network.ApiService
import io.reactivex.rxjava3.core.Single

class MovieMashRepositoryImpl(private val apiService: ApiService) : MovieMashRepository {

    val apiKey = BuildConfig.API_KEY


    override fun getMovies() =
        apiService.getMovies(apiKey = apiKey)

    override fun getTVShows() = apiService.getTVShows(apiKey = apiKey)

    override fun getReleases() = apiService.getReleases(apiKey = apiKey)

    override fun fetchMoviesAndTVShows(): Single<Pair<MovieResponse, TVShowResponse>> {
        return Single.zip(
                apiService.getMovies(apiKey = apiKey)
                ,
            apiService.getTVShows(apiKey = apiKey)
        ) { movies, tvShows -> Pair(movies, tvShows) } // Combine results

    }

    override fun getTypeData() = apiService.getTypeData(apiKey = apiKey,
        types = "")
    override fun getTypeDataCombined(): Single<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>> {
     return Single.zip(
         apiService.getTypeData(apiKey = apiKey, types =  "movie")
             ,
         apiService.getTypeData(apiKey = apiKey, types =  "tv_series")
             ,
         ){
         m, t -> Pair(m, t)
     }
    }

    override fun getDetails(titleId: String): Single<Details> = apiService.getDetails(
        apiKey = apiKey,
        titleId = titleId,
        appendToResponse = "sources"
    )
}