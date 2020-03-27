package com.pooyeshgaran.imdbsearch.feature.Search.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pooyeshgaran.imdbsearch.R
import com.pooyeshgaran.imdbsearch.feature.Detail.ui.DetailActivity
import com.pooyeshgaran.imdbsearch.utils.SearchResource
import com.pooyeshgaran.imdbsearch.feature.Search.viemodel.SearchViewModel
import com.pooyeshgaran.imdbsearch.feature.Search.adapter.SearchAdapter
import com.pooyeshgaran.imdbsearch.feature.bookmarked.MovieDetail
import com.pooyeshgaran.imdbsearch.pojo.Result
import com.pooyeshgaran.imdbsearch.utils.SearchDiffUtilsCallback
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    val searchViewModel : SearchViewModel by viewModel()
    private lateinit var adapter: SearchAdapter
    private val disposable = CompositeDisposable()
//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        imgBookmarked.setOnClickListener {
            val intent = Intent(this, MovieDetail::class.java)

            startActivity(intent)
        }
        searchData()
        searchMovie("joker")
   }

    private fun init(){
    }
    private fun searchData(){

        searchViewModel.getSearchResultData().observe(this,Observer {

                  when(it) {
                      is SearchResource.Loading -> {
                          loading.visibility = View.VISIBLE
                      }
                      is SearchResource.Error -> {
                          loading.visibility = View.INVISIBLE
                          Toast.makeText(this, "Some error wile fetching data", Toast.LENGTH_SHORT).show()
                                searchMovie(edtSearchName.text.toString())

                      }
                      is SearchResource.Success ->{
                          loading.visibility = View.INVISIBLE
                          showResultsInRecycler(it.data?.results?.toMutableList())
                      }

                  }


        })
        initRecyclerAdapter()


        imgSearch.setOnClickListener {
            searchMovie(edtSearchName.text.toString())
        }

        edtSearchName.setOnEditorActionListener { _ , actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovie(edtSearchName.text.toString())
            }
            true
        }
    }

//    private fun searchCharacter(movieName: String) {
//        searchViewModel.ShowResult(movieName)
//    }


    private fun initRecyclerAdapter(){
        adapter = SearchAdapter(SearchDiffUtilsCallback()) { movie_title: String ->
            showDetailActivity(movie_title)
        }
        recycler.adapter = adapter
    }
    private fun showResultsInRecycler(searchData: List<Result>?) {
        adapter.submitList(searchData)
    }

    private fun showDetailActivity(movie_title : String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie_title",movie_title)
        startActivity(intent)



    }
    private fun searchMovie(movieName: String) {
        searchViewModel.ShowResult(movieName)
    }
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        recycler.adapter = null
    }
}