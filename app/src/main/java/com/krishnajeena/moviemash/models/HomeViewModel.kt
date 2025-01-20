package com.krishnajeena.moviemash.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krishnajeena.moviemash.repository.MovieMashRepository
import com.krishnajeena.moviemash.data.ReleaseSuccessResponse
import io.reactivex.rxjava3.schedulers.Schedulers
import com.krishnajeena.moviemash.ui.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class HomeViewModel(private val repository: MovieMashRepository) : ViewModel() {
    private val _uiState = MutableLiveData<Result<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>>()
    val uiState: LiveData<Result<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>> get() = _uiState

    init{
       fetchData()
    }

    @SuppressLint("CheckResult")
    fun fetchData() {
        _uiState.value = Result.Loading
        repository.getTypeDataCombined()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    _uiState.value = Result.Success(result) },
                { error ->
                    _uiState.value = Result.Error(error.message ?: "Unknown Error") }
            )
    }

}