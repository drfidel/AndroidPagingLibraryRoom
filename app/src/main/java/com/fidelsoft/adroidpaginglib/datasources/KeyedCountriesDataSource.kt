package com.fidelsoft.adroidpaginglib.datasources

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.fidelsoft.adroidpaginglib.models.Country
import com.fidelsoft.adroidpaginglib.utils.CountriesDb

class KeyedCountriesDataSource: ItemKeyedDataSource<Int, Country>() {
    //load data from datasource
    private val TAG: String = "KeyedCountriesDataSource"
    private val source = CountriesDb.getCountries()

    override fun getKey(item: Country): Int {
        //use own loic or requirement to load key
        return item.id
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Country>
    ) {
        Log.v(TAG, "loadInitial called")
        val list = mutableListOf<Country>()

        for (i in 0..params.requestedLoadSize){
            if(i >source.size){
                break
            }
            list.add(source.get(i))
        }

        Log.v(TAG, "loadInitial contains ${list.size} countries, from ${list.first().name} to ${list.last().name}")
        callback.onResult(list, 0, list.size)  //position can be the las t positioin user left
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Country>) {
        Log.v(TAG, "loadAfter called")
        val key = params.key
        val list = mutableListOf<Country>()

        if(key == source.size){
            callback.onResult(list.orEmpty())
            return
        }

        val lastCountry = source.get(key)
        Log.v(TAG, "loadAfter reading from id ${key} (countryID ${lastCountry.id}, requestedSize ${params.requestedLoadSize}")
        for (i in lastCountry.id..(lastCountry.id + params.requestedLoadSize)){
            if(i == source.size){
                break
            }
            list.add(source.get(i))
        }
        Log.v(TAG, "loadAfter created a list of ${list.size} size..")

        callback.onResult(list.orEmpty())
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Country>) {
        Log.v(TAG, "loadBefore called")
        val key = params.key
        val list = mutableListOf<Country>()


        if (key <= 1){
            callback.onResult(list.orEmpty())
            return
        }
        Log.v(TAG, "loadBefore created a list of ${list.size} size for key ${params.key}...")

        callback.onResult(list)

    }
}

//create factory
class KeyedCountriesDataSourceFactory: DataSource.Factory<Int, Country>(){
    var dataSource = MutableLiveData<KeyedCountriesDataSource>()
    lateinit var latestSource: KeyedCountriesDataSource


    override fun create(): DataSource<Int, Country> {
        latestSource = KeyedCountriesDataSource()
        dataSource.postValue(latestSource)

        return latestSource
    }

}