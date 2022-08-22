package com.fidelsoft.adroidpaginglib.utils

import android.util.Log
import androidx.paging.PagedList
import com.fidelsoft.adroidpaginglib.models.Country

class CountriesBoundaryCallback: PagedList.BoundaryCallback<Country>() {
    private val TAG: String = "CountriesBoundaryCallBack"

    override fun onZeroItemsLoaded() {
        //super.onZeroItemsLoaded()
        Log.i(TAG, "OnZeroItemsLoaded" )
    }

    override fun onItemAtFrontLoaded(itemAtFront: Country) {
        //super.onItemAtFrontLoaded(itemAtFront)
        Log.i(TAG, "OnItemAtFrontLoaded" )
    }

    override fun onItemAtEndLoaded(itemAtEnd: Country) {
        //super.onItemAtEndLoaded(itemAtEnd)
        Log.i(TAG, "OnItemAtEndLoaded" )
    }
}