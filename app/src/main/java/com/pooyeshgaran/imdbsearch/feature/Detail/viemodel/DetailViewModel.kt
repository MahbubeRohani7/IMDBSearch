package com.pooyeshgaran.imdbsearch.feature.Detail.viemodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pooyeshgaran.imdbsearch.repository.local.RoomProvider
import com.pooyeshgaran.imdbsearch.utils.SearchResource
import com.pooyeshgaran.imdbsearch.pojo.MovieDataBaseEntity
import com.pooyeshgaran.imdbsearch.pojo.Result
import com.pooyeshgaran.imdbsearch.pojo.SearchResponse
import com.pooyeshgaran.imdbsearch.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailViewModel(val repository: Repository,val database: RoomProvider) : ViewModel() {
    //    private val classA: KoinModel by inject()
//    val vm: SearchViewModel by viewModel()

    private val getMoviesave: List<SearchResponse>? = null
    private val movieData: MovieDataBaseEntity? = null
    private val detailsResponseData = MutableLiveData<SearchResource<SearchResponse>>()



    private val disposable = CompositeDisposable()
//    var isAlbumSaved = false

    fun getDetail(movie_title: String) {
        disposable.add(
                repository.getMovieDetails(movie_title)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            detailsResponseData.value = SearchResource.Success(it)
                        },
                                {
                                    Log.d("TAGError", it.message)
                                    detailsResponseData.value = SearchResource.Error(it.message.orEmpty())
                                }))

    }

    fun getSearchResultData(): LiveData<SearchResource<SearchResponse>> = detailsResponseData

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

      fun saveBook(movieDetailSave: List<Result>?) {
          GlobalScope.launch {

              database.baseDao().insert(
                         MovieDataBaseEntity(
                                 item_id = movieDetailSave!!.get(0).id,
                                    overview = movieDetailSave!!.get(0).overview,
                                    movie_banner = movieDetailSave!!.get(0).backdrop_path
                                    , poster_path = movieDetailSave!!.get(0).poster_path
                                    , title = movieDetailSave!!.get(0).title
                                    , vote_average = movieDetailSave!!.get(0).vote_average))




              val data = database.baseDao().getAllBookmarks()
              data.forEach {
                  println(it.toString())
                  Log.d("TAG",it.toString())
              }
          }


    }

//    private fun removeBookmark(id: Int) {
////        if (movieData != null)
////            disposable.add(
////                    repository.removeAlbum(movieData.title)
////                            .subscribe({
////
////                                isAlbumSaved = false
////                                Log.d("saved", "saved")
////                            }, {
////                                Log.d("removedata", it.toString())
//////                                viewNotifier.value = ViewNotifierEnums.ERROR_REMOVING_DATA
////                            })
////            )
////        else
////            Log.d("no data", "ERROR_DATA_NOT_AVAILABLE")
//////            viewNotifier.value = ViewNotifierEnums.ERROR_DATA_NOT_AVAILABLE
//    }

//    fun onClickedOnSaveButton(listMovieResult: List<Result>?) {
//
//    }
}