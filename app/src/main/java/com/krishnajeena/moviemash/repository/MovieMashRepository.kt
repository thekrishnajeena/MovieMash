package com.krishnajeena.moviemash.repository

import com.krishnajeena.moviemash.data.Details
import com.krishnajeena.moviemash.data.MovieResponse
import com.krishnajeena.moviemash.data.ReleaseSuccessResponse
import com.krishnajeena.moviemash.data.TVShowResponse
import io.reactivex.rxjava3.core.Single

interface MovieMashRepository{

    fun getTypeDataCombined(): Single<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>

    fun getDetails(titleId: String): Single<Details>
}