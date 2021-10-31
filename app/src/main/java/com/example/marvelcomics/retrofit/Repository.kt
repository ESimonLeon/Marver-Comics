package com.example.marvelcomics.retrofit

import com.example.marvelcomics.AppConstants.APIKEY
import com.example.marvelcomics.AppConstants.HASH
import com.example.marvelcomics.AppConstants.OFFSET
import com.example.marvelcomics.AppConstants.TS

class Repository(private val apiServiceInterface: ApiServiceInterface) {

    suspend fun getComics(limitComics: Int?) =
        apiServiceInterface.getComicsList(TS, APIKEY, HASH, OFFSET, limitComics!!)


    suspend fun getDetailComic(comic: Int?) =
        apiServiceInterface.getComicDetail(comic!!, TS, APIKEY, HASH)
}
