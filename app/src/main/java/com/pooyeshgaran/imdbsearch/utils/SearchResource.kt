package com.pooyeshgaran.imdbsearch.utils


sealed  class SearchResource<T>(
    val data: T? = null,
    val message: String? = null
    ) {
        /**
         * When we get the response successfully
         */
        class Success<T>(data: T) : SearchResource<T>(data)

        /**
         * When we want to show the loading
         */
        class Loading<T>(data: T? = null) : SearchResource<T>(data)

        /**
         * When there is an error while getting the data
         */
        class Error<T>(message: String, data: T? = null) : SearchResource<T>(data, message)
    }

