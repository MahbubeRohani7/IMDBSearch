package com.pooyeshgaran.imdbsearch.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.room.Room
import com.pooyeshgaran.imdbsearch.repository.local.RoomProvider
import com.pooyeshgaran.imdbsearch.koin.RoomModule
//import androidx.multidex.MultiDex
import com.pooyeshgaran.imdbsearch.koin.classModule
import com.pooyeshgaran.imdbsearch.koin.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


//import dagger.android.support.DaggerApplication

class BaseApplication  : Application(){
        override fun onCreate() {
            super.onCreate()
//            MultiDex.install(applicationContext)
            startKoin {
                androidLogger()
                androidContext(this@BaseApplication)
                modules(classModule, vmModule, RoomModule)
            }
            val db = Room.databaseBuilder(
                    applicationContext,
                    RoomProvider::class.java, "movie.db"
            ).allowMainThreadQueries().build()

        }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this);
    }
}