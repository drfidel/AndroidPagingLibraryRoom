package com.fidelsoft.adroidpaginglib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fidelsoft.adroidpaginglib.adapters.CountryAdapter
import com.fidelsoft.adroidpaginglib.models.Country
import com.fidelsoft.adroidpaginglib.utils.CountriesDb
import com.fidelsoft.adroidpaginglib.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val TAG: String = "ActivityMainObserved"
    private val viewModel: MainActivityViewModel = MainActivityViewModel()
    private var topId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //prepare countries
        CountriesDb.initialize(this)

        //connect to recyclerview
        var countriesRV = findViewById<RecyclerView>(R.id.RVCountryList)
        val adapter = CountryAdapter()

        countriesRV.layoutManager = LinearLayoutManager(this)
        countriesRV.adapter = adapter

        //add the viewmodel
        viewModel.countries.observe(this, Observer<PagedList<Country>> { countries ->
            Log.v(TAG, "Observed ${countries.size} countries from viewModel...")
            adapter.submitList(countries)
        })
    }

    fun deleteTop(view: View){
       
        viewModel.dataSource.deleteByID(topId)
        topId++
    }
}