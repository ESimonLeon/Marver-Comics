package com.example.marvelcomics

import com.example.marvelcomics.AppConstants.IMG_VARIANT_FAN
import com.example.marvelcomics.AppConstants.IMG_VARIANT_MED
import com.example.marvelcomics.retrofit.response.ComicThumbnail

fun getItemImageComic(comicThumbnail: ComicThumbnail): String {
    return comicThumbnail.path.plus(IMG_VARIANT_MED).plus(comicThumbnail.extension)
}

fun getDetailImageComic(comicThumbnail: ComicThumbnail): String {
    return comicThumbnail.path.plus(IMG_VARIANT_FAN).plus(comicThumbnail.extension)
}