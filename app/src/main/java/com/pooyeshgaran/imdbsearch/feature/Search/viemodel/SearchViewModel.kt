package com.pooyeshgaran.imdbsearch.feature.Search.viemodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pooyeshgaran.imdbsearch.pojo.SearchResponse
import com.pooyeshgaran.imdbsearch.repository.Repository
import com.pooyeshgaran.imdbsearch.utils.SearchResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel (private val dataRepository: Repository):ViewModel() {
//    val myModule : Module = module {
//        viewModel { SearchViewModel(get()) }
//        single { Repository(Network(get()),get<RoomProvider>().baseDao()) }
//    }


    private val searchNameData = MutableLiveData<SearchResource<SearchResponse>>()
    var disposable = CompositeDisposable()


    fun ShowResult(movieName : String){
        searchNameData.value = SearchResource.Loading()

        disposable.add(
                dataRepository.searchMovie(movieName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                  searchNameData.value = SearchResource.Success(it)
                },{

                   Log.d("TAGError",it.message)
                    searchNameData.value = SearchResource.Error(it.message.orEmpty())

                }))

    }

    fun getSearchResultData():LiveData<SearchResource<SearchResponse>> = searchNameData
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}