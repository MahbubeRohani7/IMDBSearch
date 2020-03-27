package com.pooyeshgaran.imdbsearch.utils

import androidx.recyclerview.widget.DiffUtil
import com.pooyeshgaran.imdbsearch.pojo.Result

class SearchDiffUtilsCallback: DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
    ) = oldItem.title == newItem.title

    override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
    ) = oldItem.title == newItem.title
}
