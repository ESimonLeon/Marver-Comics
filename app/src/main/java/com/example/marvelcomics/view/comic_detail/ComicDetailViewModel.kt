package com.example.marvelcomics.view.comic_detail

import android.util.Log.wtf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomics.retrofit.Repository
import com.example.marvelcomics.retrofit.response.ComicListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ComicDetailViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _comicsListResponse = MutableLiveData<ComicListResponse>()
    val comicsListResponse: LiveData<ComicListResponse> get() = _comicsListResponse

    fun getDetailComic(idComic: Int) = viewModelScope.launch {
        val repository = withContext(IO) {
            repository.getDetailComic(idComic)
        }
        createResult(repository)
    }

    private fun createResult(repository: Response<ComicListResponse>) {
        if (repository.isSuccessful) {
            _comicsListResponse.postValue(repository.body())
        }
    }


}