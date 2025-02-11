package com.example.mangaapp

data class Manga(
    val title: String,
    val author: String,
    val genre: String,
    val chaptersRead: Int,
    val totalChapters: Int,
    val posterUrl: String?,
)