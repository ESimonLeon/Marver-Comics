package com.example.marvelcomics.retrofit

object ApiRoutes {

    //base url
    const val BASE_URL = "https://gateway.marvel.com:443/"

    const val COMICS_LIST = "/v1/public/comics"
    const val COMICS_DETAIL = "$COMICS_LIST/{comic}"
}