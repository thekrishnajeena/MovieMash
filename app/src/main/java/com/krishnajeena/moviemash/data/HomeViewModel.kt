package com.krishnajeena.moviemash.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import com.krishnajeena.moviemash.ui.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class HomeViewModel(private val repository: MovieMashRepository) : ViewModel() {
    private val _uiState = MutableLiveData<Result<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>>()
    val uiState: LiveData<Result<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>> get() = _uiState

    private val _uiStateRR = MutableLiveData<Result<ReleaseSuccessResponse>>()
    val uiStateRR : LiveData<Result<ReleaseSuccessResponse>> get() = _uiStateRR

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

    @SuppressLint("CheckResult")
    fun fetchReleaseData() {
        _uiStateRR.value = Result.Loading
        repository.getReleases()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.i("TAG:::::", "Fetched data: $result")
                    _uiStateRR.value = Result.Success(result) },
                { error ->
                    Log.e("TAG:::::", "Error fetching data: ${error.message}")
                    _uiStateRR.value = Result.Error(error.message ?: "Unknown Error") }
            )
        Log.i("TAG:::::HMV", uiStateRR.value.toString())
    }


}