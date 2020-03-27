package com.pooyeshgaran.imdbsearch.feature.Search.model

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pooyeshgaran.imdbsearch.repository.network.SearchMovieRetrofit
import com.pooyeshgaran.imdbsearch.repository.network.ApiWorker
import com.pooyeshgaran.imdbsearch.utils.Constant
import retrofit2.Retrofit


class SearchModel  ( var retrofit: Retrofit) {

    fun setSearchInterface(): SearchMovieRetrofit {
        retrofit = Retrofit.Builder().
                baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ApiWorker.gsonConverter)
//                .client(ApiWorker.client)
                .build()

        return retrofit.create(SearchMovieRetrofit::class.java)
    }




}