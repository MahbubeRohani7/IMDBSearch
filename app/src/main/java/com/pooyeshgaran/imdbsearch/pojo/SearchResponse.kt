package com.pooyeshgaran.imdbsearch.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class SearchResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
@Entity
data class Result(
        @PrimaryKey(autoGenerate = true)

        @ColumnInfo(name = "note_content")
    val adult: Boolean,


    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
        @ColumnInfo(name = "overview")
    val overview: String,

    val popularity: Double,

        @ColumnInfo(name = "movieposter")
    val poster_path: String,
        @ColumnInfo(name = "movierelease")
    val release_date: String,

        @ColumnInfo(name = "title")
    val title: String,

    val video: Boolean,
        @ColumnInfo(name = "vote_average")
    val vote_average: String,

        @ColumnInfo(name = "vote_count")
    val vote_count: Int
)