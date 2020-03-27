package com.pooyeshgaran.imdbsearch.feature.Detail.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.pooyeshgaran.imdbsearch.R
import com.pooyeshgaran.imdbsearch.feature.Detail.viemodel.DetailViewModel
import com.pooyeshgaran.imdbsearch.repository.local.RoomProvider
import com.pooyeshgaran.imdbsearch.utils.SearchResource
import com.pooyeshgaran.imdbsearch.pojo.MovieDataBaseEntity
import com.pooyeshgaran.imdbsearch.pojo.Result
import com.pooyeshgaran.imdbsearch.utils.Constant
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(),View.OnClickListener {
    val detailViewModel: DetailViewModel by viewModel()
    var movieDetailSave: List<Result>? = null
        var liked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        val movie_title: String = intent.getStringExtra("movie_title")
        init()
        searchMovie(movie_title)
        searchData()



        if (!liked){
            liked=true
        addBookmark.setOnClickListener {
            addBookmark.setImageResource(R.drawable.fav)
            val db = RoomProvider(this)
//            detailViewModel.saveBook(movieDetailSave)

                    if (movieDetailSave != null) {

                        GlobalScope.launch {
                            db.baseDao().insert(MovieDataBaseEntity(item_id = movieDetailSave!!.get(0).id,
                                    overview = movieDetailSave!!.get(0).overview,
                                    movie_banner = movieDetailSave!!.get(0).backdrop_path
                                    , poster_path = movieDetailSave!!.get(0).poster_path
                                    , title = movieDetailSave!!.get(0).title
                                    , vote_average = movieDetailSave!!.get(0).vote_average))
                        }
                        Toast.makeText(this,"textsaved"+movieDetailSave!!.get(0).overview,Toast.LENGTH_SHORT).show()

                    }
        }}

    }

    private fun init() {


    }

    private fun searchData() {

        detailViewModel.getSearchResultData().observe(this, Observer {
//            addBookmark.setOnClickListener {
//
//                detailViewModel.saveBook(movieDetailSave)
//            }
            when (it) {
                is SearchResource.Loading -> {
//                    loading.visibility = View.VISIBLE
                    Toast.makeText(this, "Some loaing", Toast.LENGTH_LONG).show()
                }
                is SearchResource.Error -> {
//                    loading.visibility = View.INVISIBLE
                    Toast.makeText(this, "Some error wile fetching data", Toast.LENGTH_LONG).show()
//                    searchMovie(edtSearchName.text.toString())
                }
                is SearchResource.Success -> {
//                    loading.visibility = View.INVISIBLE
                    setDetail(it.data?.results?.toMutableList())
                    movieDetailSave = it.data?.results?.toMutableList()
                    Toast.makeText(this, "Some  wile load data", Toast.LENGTH_LONG).show()
                }

            }



        })


    }
//    fun saveBook1(movieDataBaseEntity: MovieDataBaseEntity) {
//        var disposable2 = CompositeDisposable()
//                disposable2.add(
//                saveBook2(movieDataBaseEntity!!)
//                        .subscribe({
//                            Log.d("SAVING_DATA", "rttoo")
//
//                        }, {
//
//                            Log.d("ERROR_SAVING_DATA", "rttoo")
//                        })
//        )
//
//
//    }

//    fun saveBook2(movieDataBaseEntity: MovieDataBaseEntity) : Completable {
//        val db = Room.databaseBuilder(
//                applicationContext,
//                RoomProvider::class.java, "movie.db"
//        ).build()
//        return db.baseDao().Bookmark(movieDataBaseEntity)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//
//    }

    private fun setDetail(searchData: List<Result>?){

        if (searchData != null) {
            movie_name_txt.text=searchData.get(0).title
            Glide.with(this)
                    .asBitmap().load(Constant.Image_Url + searchData.get(0).backdrop_path)
                    .into(movie_backdrop_image)
            movie_description.text = searchData.get(0).overview
            Glide.with(this)
                    .asBitmap().load(Constant.Image_Url + searchData.get(0).poster_path)
                    .into(movie_poster_img)


            movie_rate.text = searchData.get(0).vote_average
            movie_release.text = searchData.get(0).release_date
        }
    }
    private fun searchMovie(movie_title: String) {
        detailViewModel.getDetail(movie_title)
    }

//
    override fun onClick(p0: View?) {

    }
}
