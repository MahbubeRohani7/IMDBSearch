package com.pooyeshgaran.imdbsearch.repository

import com.pooyeshgaran.imdbsearch.pojo.SearchResponse
import com.pooyeshgaran.imdbsearch.repository.network.Network
import io.reactivex.Observable

class Repository  (private val networkRepository:Network  )  {



    fun searchMovie(movieName: String): Observable<SearchResponse> {
        return networkRepository.setMovieDaggerObservable(movieName)
    }

    fun getMovieDetails(movie_title: String): Observable<SearchResponse> {
        return networkRepository.getMovieDetails(movie_title)
    }
//    fun getAlbumDetails( movieName: String): Single<MovieDataBaseEntity> {
//        return db.baseDao().getSpecificBookmark(movieName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
//    fun getAllSavedAlbums(): Single<List<MovieDataBaseEntity>> {
//        return db.baseDao().getAllBookmarks()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
//
//    fun saveAlbum(albumDatabaseEntity: MovieDataBaseEntity): Completable {
//        return db.baseDao().Bookmark(albumDatabaseEntity)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
//    fun removeAlbum(movieName: String): Completable {
//        return db.baseDao().removeBookmark( movieName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }



//


}