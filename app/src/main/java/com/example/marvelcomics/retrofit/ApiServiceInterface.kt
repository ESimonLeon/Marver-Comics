package com.example.marvelcomics.retrofit

import com.example.marvelcomics.AppConstants.PATH_COMIC
import com.example.marvelcomics.AppConstants.QUERY_APIKEY
import com.example.marvelcomics.AppConstants.QUERY_HASH
import com.example.marvelcomics.AppConstants.QUERY_LIMIT
import com.example.marvelcomics.AppConstants.QUERY_OFFSET
import com.example.marvelcomics.AppConstants.QUERY_TS
import com.example.marvelcomics.retrofit.ApiRoutes.COMICS_DETAIL
import com.example.marvelcomics.retrofit.ApiRoutes.COMICS_LIST
import com.example.marvelcomics.retrofit.response.ComicListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET(COMICS_LIST)
    suspend fun getComicsList(
        @Query(QUERY_TS) ts: Int,
        @Query(QUERY_APIKEY) apikey: String,
        @Query(QUERY_HASH) hash: String?,
        @Query(QUERY_OFFSET) offset: Int?,
        @Query(QUERY_LIMIT) limit: Int
    ): Response<ComicListResponse>


    @GET(COMICS_DETAIL)
    suspend fun getComicDetail(
        @Path(PATH_COMIC) comic: Int,
        @Query(QUERY_TS) ts: Int,
        @Query(QUERY_APIKEY) apikey: String,
        @Query(QUERY_HASH) hash: String?
    ): Response<ComicListResponse>

}
