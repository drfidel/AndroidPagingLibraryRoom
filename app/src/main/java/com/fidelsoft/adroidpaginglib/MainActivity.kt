package com.fidelsoft.adroidpaginglib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.afzaalahmadzeeshan.android.paging.data.ContactDatabase
import com.fidelsoft.adroidpaginglib.adapters.ContactsAdapter
import com.fidelsoft.adroidpaginglib.models.Contact
import com.fidelsoft.adroidpaginglib.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ready the database
        val db = Room.databaseBuilder(this, ContactDatabase::class.java, "contacts.sqlite")
            .allowMainThreadQueries()
            .build()
        viewModel = MainActivityViewModel(db)

        // Connect to RecyclerView
        val contactsRv = findViewById<RecyclerView>(R.id.RVCountryList)
        val adapter = ContactsAdapter()

        contactsRv.layoutManager = LinearLayoutManager(this)
        contactsRv.adapter = adapter

        viewModel.contacts.observe(this, Observer { contacts ->
            Log.v(TAG, "Observed ${contacts.size} contacts from ViewModel")
            adapter.submitList(contacts)
        })
    }

    fun deleteTop(view: View) {
        val topContact = viewModel.dataSource.getTopContact()
        viewModel.dataSource.delete(topContact)
    }

    val firstNames = arrayOf("Steve", "Bill", "Mike", "George", "John", "Chris")
    val lastNames = arrayOf("Mathers", "Hanlon", "Taylor", "Smith", "Trevolta")
    fun addContact(view: View?) {
        val name = "${firstNames.random()} ${lastNames.random()}";
        val lastId = viewModel.dataSource.getLastId()
        Log.i(TAG, "lastId is ${lastId}")
        val contact = Contact(lastId + 1, name)

        Log.i(TAG, "inserting contact to the database ${contact}")
        viewModel.dataSource.insertContact(contact)
    }



//    private val TAG: String = "ActivityMainObserved"
//    private val viewModel: MainActivityViewModel = MainActivityViewModel()
//    private var topId = 1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        //prepare countries
//        CountriesDb.initialize(this)
//
//        //connect to recyclerview
//        var countriesRV = findViewById<RecyclerView>(R.id.RVCountryList)
//        val adapter = CountryAdapter()
//
//        countriesRV.layoutManager = LinearLayoutManager(this)
//        countriesRV.adapter = adapter
//
//        //add the viewmodel
//        viewModel.countries.observe(this, Observer<PagedList<Country>> { countries ->
//            Log.v(TAG, "Observed ${countries.size} countries from viewModel...")
//            adapter.submitList(countries)
//        })
//    }
//
//    fun deleteTop(view: View){
//
//        viewModel.dataSource.deleteByID(topId)
//        topId++
//    }
}