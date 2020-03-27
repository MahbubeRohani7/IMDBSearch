package com.pooyeshgaran.imdbsearch.feature.bookmarked

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pooyeshgaran.imdbsearch.R
import com.pooyeshgaran.imdbsearch.repository.local.RoomProvider
import com.pooyeshgaran.imdbsearch.feature.Detail.ui.DetailActivity
import com.pooyeshgaran.imdbsearch.feature.bookmarked.adapter.OfflineAdapter
import com.pooyeshgaran.imdbsearch.pojo.MovieDataBaseEntity
import kotlinx.android.synthetic.main.activity_main.recycler
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetail : AppCompatActivity() {
    private lateinit var adapter: OfflineAdapter
    private lateinit var resultList: List<MovieDataBaseEntity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val db = RoomProvider(this)
        GlobalScope.launch {
            resultList = db.baseDao().getAllBookmarks()
        }
        linearLayouDetail.setOnClickListener {
            initRecyclerAdapter(resultList)
        }


    }
    private fun initRecyclerAdapter(resultList: List<MovieDataBaseEntity>){
        if (resultList!=null) {
            adapter = OfflineAdapter(resultList)

            recycler.adapter = adapter
        }
    }
    private fun showResultsInRecycler(searchData: List<MovieDataBaseEntity>) {

    }

    private fun showDetailActivity(movie_title : String){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie_title",movie_title)
        startActivity(intent)
    }
}
