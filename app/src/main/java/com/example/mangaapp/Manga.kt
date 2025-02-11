package com.example.mangaapp.data

import java.io.Serializable

data class Manga(
    val title: String,
    val author: String,
    val genre: String,
    val chaptersRead: Int,
    val totalChapters: Int,
    val posterUrl: String?,
    val description: String,
    val rating: Double
) : Serializable // ✅ Change from Parcelable to Serializable
