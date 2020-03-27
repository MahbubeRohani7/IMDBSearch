package com.pooyeshgaran.imdbsearch.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pooyeshgaran.imdbsearch.utils.StringListDataConverter
import com.pooyeshgaran.imdbsearch.pojo.MovieDataBaseEntity


@Database(entities = [MovieDataBaseEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListDataConverter::class)


    abstract class RoomProvider : RoomDatabase() {
        abstract fun baseDao(): BaseDao


    companion object {
        @Volatile private var instance: RoomProvider? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance
                ?: synchronized(LOCK){
            instance
                    ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                RoomProvider::class.java, "movie.db")
                .build()
    }
}



