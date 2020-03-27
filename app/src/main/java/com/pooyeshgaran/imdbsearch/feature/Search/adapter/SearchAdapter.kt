package com.pooyeshgaran.imdbsearch.feature.Search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pooyeshgaran.imdbsearch.R
import com.pooyeshgaran.imdbsearch.pojo.Result
import com.pooyeshgaran.imdbsearch.utils.Constant
import com.pooyeshgaran.imdbsearch.utils.SearchDiffUtilsCallback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class SearchAdapter(resultList:SearchDiffUtilsCallback
,private val clickListener :(String) ->Unit):
        ListAdapter<Result, SearchAdapter.ViewHolder>(resultList) {

    inner class ViewHolder(private val view :View) : RecyclerView.ViewHolder(view){
        fun bind(result: Result){

            Picasso.get().load(Constant.Image_Url + result.poster_path)

                    .into(view.image_movie_poster)

                view.movie_name.text = result.title
            view.setOnClickListener{clickListener(result.title)}
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
          }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(getItem(position))
    }


}