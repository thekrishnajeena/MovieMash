package com.krishnajeena.moviemash.data

import android.util.Log
import com.krishnajeena.moviemash.network.ApiService
import io.reactivex.rxjava3.core.Single

class MovieMashRepositoryImpl(private val apiService: ApiService) : MovieMashRepository {
    override fun getMovies() =
        apiService.getMovies(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ")
            .doOnSuccess { response ->
        Log.i("TAG::::", "Movies API Response: $response")
    }
    override fun getTVShows() = apiService.getTVShows(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ")

    override fun getReleases() = apiService.getReleases(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ")

    override fun fetchMoviesAndTVShows(): Single<Pair<MovieResponse, TVShowResponse>> {
        return Single.zip(
                apiService.getMovies(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ")
                .doOnSuccess { response ->
                    Log.i("TAG::::", "Movies API Response: $response")},
            apiService.getTVShows(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ")
        ) { movies, tvShows -> Pair(movies, tvShows) } // Combine results

    }

    override fun getTypeData() = apiService.getTypeData(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ",
        types = "")
    override fun getTypeDataCombined(): Single<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>> {
     return Single.zip(
         apiService.getTypeData(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ", types =  "movie")
             .doOnSuccess { re ->
                 Log.i("TAG:::MOVIE:::", re.toString())
             },
         apiService.getTypeData(apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ", types =  "tv_series")
             ,
         ){
         m, t -> Pair(m, t)
     }
    }

    override fun getDetails(titleId: String): Single<Details> = apiService.getDetails(
        apiKey = "qgmqEMrZ0YvuILwkQD6ROsd4KgiOtRa9XJtbO8tJ",
        titleId = titleId,
        appendToResponse = "sources"
    )
}