package com.pooyeshgaran.imdbsearch.repository.network

import com.pooyeshgaran.imdbsearch.feature.Search.model.SearchModel
import com.pooyeshgaran.imdbsearch.pojo.SearchResponse
import io.reactivex.Observable

class Network  ( private var searchModel : SearchModel){
    fun setMovieDaggerObservable(movieName: String): Observable<SearchResponse>
    {
        return searchModel.setSearchInterface().getTimings(movieName)
    }

    fun getMovieDetails(movie_title: String): Observable<SearchResponse> {
        return searchModel.setSearchInterface().getTimings(movie_title)

    }
}