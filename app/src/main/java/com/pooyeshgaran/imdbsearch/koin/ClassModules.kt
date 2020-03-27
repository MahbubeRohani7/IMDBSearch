package com.pooyeshgaran.imdbsearch.koin

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pooyeshgaran.imdbsearch.feature.Search.model.SearchModel
import com.pooyeshgaran.imdbsearch.repository.Repository
import com.pooyeshgaran.imdbsearch.repository.network.ApiWorker
import com.pooyeshgaran.imdbsearch.repository.network.Network
import com.pooyeshgaran.imdbsearch.utils.Constant
import org.koin.dsl.module
import retrofit2.Retrofit

val classModule = module {
    single {
        Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ApiWorker.gsonConverter)
                .client(ApiWorker.client)
                .build()
    }
    //RoomDataBase
//    single {
//        Room.databaseBuilder(androidApplication(), RoomProvider::class.java,
//                "movie-db")
//                .build()
//
//    }

    single { SearchModel(get()) }

    single { Repository(Network(get())) as Repository}


}
