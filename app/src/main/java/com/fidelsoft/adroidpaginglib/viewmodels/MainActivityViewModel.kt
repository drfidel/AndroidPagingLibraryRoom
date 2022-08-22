package com.fidelsoft.adroidpaginglib.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.fidelsoft.adroidpaginglib.datasources.PagedCountriesDataSourceFactory
import com.fidelsoft.adroidpaginglib.models.Country
import com.fidelsoft.adroidpaginglib.utils.CountriesBoundaryCallback

class MainActivityViewModel: ViewModel() {

    val pageSize = 15
    var config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(15)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    var dataSource = PagedCountriesDataSourceFactory()

    //normal execution of livedata
//    var countries: LiveData<PagedList<Country>> = LivePagedListBuilder(dataSource, config)
//        .setBoundaryCallback(CountriesBoundaryCallback())
//        .build()

    //using ktx extentions
    var countries: LiveData<PagedList<Country>> = dataSource
        .toLiveData(config,
        null,
        CountriesBoundaryCallback())
}