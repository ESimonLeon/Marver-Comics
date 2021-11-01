package com.example.marvelcomics.view.comic_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcomics.retrofit.ApiRepository
import com.example.marvelcomics.retrofit.response.ComicListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _comicsListResponse = MutableLiveData<ComicListResponse>()
    val comicsListResponse: LiveData<ComicListResponse> get() = _comicsListResponse

    private val _updateLoadComics = MutableLiveData(false)
    val updateLoadComics: LiveData<Boolean> get() = _updateLoadComics

    private val _communicationResult = MutableLiveData<Boolean>()
    val communicationResult: LiveData<Boolean> get() = _communicationResult

    private val _limitComics = MutableLiveData(20)
    val limitComics: LiveData<Int> get() = _limitComics

    init {
        refreshList()
    }

    fun refreshList() {
        _updateLoadComics.postValue(true)
        loadComics()
    }

    fun loadComics() = viewModelScope.launch {
        val repository = withContext(IO) {
            apiRepository.getComics(limitComics.value)
        }
        _updateLoadComics.postValue(false)
        createResult(repository)
    }

    private fun createResult(repository: Response<ComicListResponse>) =
        _communicationResult.apply {
            postValue(repository.isSuccessful)
            if (repository.isSuccessful) {
                _updateLoadComics.postValue(false)
                _comicsListResponse.postValue(repository.body())
            }
        }

}
