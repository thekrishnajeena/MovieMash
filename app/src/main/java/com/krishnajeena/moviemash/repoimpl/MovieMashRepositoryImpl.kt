package com.krishnajeena.moviemash.repoimpl

import com.krishnajeena.moviemash.BuildConfig
import com.krishnajeena.moviemash.data.Details
import com.krishnajeena.moviemash.data.MovieResponse
import com.krishnajeena.moviemash.data.ReleaseSuccessResponse
import com.krishnajeena.moviemash.data.TVShowResponse
import com.krishnajeena.moviemash.network.ApiService
import com.krishnajeena.moviemash.repository.MovieMashRepository
import io.reactivex.rxjava3.core.Single

class MovieMashRepositoryImpl(private val apiService: ApiService) : MovieMashRepository {

    val apiKey = BuildConfig.API_KEY

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