package com.pooyeshgaran.imdbsearch.koin

import androidx.room.Room
import com.pooyeshgaran.imdbsearch.repository.local.RoomProvider
import org.koin.dsl.module

val RoomModule = module {
    single {
        Room.databaseBuilder(get(), RoomProvider::class.java, "movie").build()

    }
    single { get<RoomProvider>().baseDao() }
}