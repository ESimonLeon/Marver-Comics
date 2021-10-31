package com.example.marvelcomics.retrofit.response

data class ComicListResponse(
    val code: Int = 0,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: ComicData
)

data class ComicData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<ComicDetail>
)

data class ComicDetail(
    val id: Int,
    val digitalId: Int,
    val title: String?,
    val issueNumber: Int,
    val variantDescription: String,
    val description: String,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val textObjects: ArrayList<TextObject>,
    val resourceURI: String,
    val urls: ArrayList<ComicUrl>,
    val series: ComicSeries,
    val dates: ArrayList<ComicDates>,
    val prices: ArrayList<ComicPrices>,
    val thumbnail: ComicThumbnail,
    val images: ArrayList<ComicThumbnail>,
    val creators: ComicCollection,
    val characters: ComicCollection,
    val stories: ComicCollection,
    val events: ComicCollection
)

data class TextObject(val type: String?, val language: String?, val text: String?)

data class ComicUrl(val type: String?, val url: String?)

data class ComicSeries(val resourceURI: String?, val name: String?)

data class ComicDates(val type: String?, val date: String?)

data class ComicPrices(val type: String?, val price: String?)

data class ComicThumbnail(val path: String?, val extension: String?)

data class ComicCollection(
    val available: Int,
    val collectionURI: String?,
    val items: ArrayList<ComicItem>,
    val returned: Int
)

data class ComicItem(val resourceURI: String, val name: String, val type: String, val role: String)