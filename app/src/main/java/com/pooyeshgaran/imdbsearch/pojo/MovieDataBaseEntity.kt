package com.pooyeshgaran.imdbsearch.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieDataBaseEntity(

        @PrimaryKey(autoGenerate = true)
        var item_id: Int?,

        @ColumnInfo(name = "overview")
        var overview: String,

        @ColumnInfo(name = "movieposter")
        var poster_path: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "vote_average")
        var vote_average: String,

        @ColumnInfo(name = "movie_banner")
        var movie_banner: String

)