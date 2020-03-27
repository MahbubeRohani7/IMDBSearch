package com.pooyeshgaran.imdbsearch.repository.network

import com.pooyeshgaran.imdbsearch.pojo.SearchResponse
import com.pooyeshgaran.imdbsearch.utils.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchMovieRetrofit {

    @GET("search/movie?api_key=44d21a036eb4bfc80a1a92913a200252&")
    fun getTimings(

            @Query("query")query: String)
            : Observable<SearchResponse>


    @GET("movie/{movie_id}api_key=44d21a036eb4bfc80a1a92913a200252&language=en-US")
    fun getMovieDetails(
            @Path("movie_id") movieId: Int
    ):Observable<SearchResponse>

}