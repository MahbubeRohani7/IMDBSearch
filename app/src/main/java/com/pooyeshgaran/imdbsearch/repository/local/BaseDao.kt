package com.pooyeshgaran.imdbsearch.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pooyeshgaran.imdbsearch.pojo.MovieDataBaseEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface BaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(albumDatabaseEntity: MovieDataBaseEntity)

    @Query("SELECT * FROM Movie")
    fun getAllBookmarks(): List<MovieDataBaseEntity>

    @Query("SELECT * FROM Movie WHERE title = :title LIMIT 1")
    fun getSpecificBookmark(title: String):List<MovieDataBaseEntity>


    @Query("DELETE from Movie where title = :title")
    fun removeBookmark(title: String)
}