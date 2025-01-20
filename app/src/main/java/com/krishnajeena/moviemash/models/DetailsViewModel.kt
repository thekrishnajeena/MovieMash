package com.krishnajeena.moviemash.models

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krishnajeena.moviemash.data.Details
import com.krishnajeena.moviemash.repository.MovieMashRepository
import com.krishnajeena.moviemash.ui.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailsViewModel(private val movieMashRepository: MovieMashRepository) : ViewModel() {

    private val _uiState = MutableLiveData<Result<Details>>()
    val uiState: LiveData<Result<Details>> get() = _uiState

    @SuppressLint("CheckResult")
    fun fetchDetails(id: String){
        _uiState.value = Result.Loading
        movieMashRepository.getDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(     { result ->
                Log.i("TAG:::::", "Fetched data: $result")
                _uiState.value = Result.Success(result) },
                { error ->
                    Log.e("TAG:::::", "Error fetching data: ${error.message}")
                    _uiState.value = Result.Error(error.message ?: "Unknown Error") }
            )
    }
}