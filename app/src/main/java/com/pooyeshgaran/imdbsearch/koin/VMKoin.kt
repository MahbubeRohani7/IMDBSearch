package com.pooyeshgaran.imdbsearch.koin
import com.pooyeshgaran.imdbsearch.feature.Detail.viemodel.DetailViewModel
import com.pooyeshgaran.imdbsearch.feature.Search.viemodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module{
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}
