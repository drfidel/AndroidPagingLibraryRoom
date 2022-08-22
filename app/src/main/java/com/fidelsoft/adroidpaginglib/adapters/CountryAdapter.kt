package com.fidelsoft.adroidpaginglib.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fidelsoft.adroidpaginglib.models.Country
import com.fidelsoft.adroidpaginglib.viewholders.CountryViewHolder

class CountryAdapter: PagedListAdapter<Country, CountryViewHolder>(COUNTRY_DIFFUTIL_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)

        if(country != null) {
            holder.bind(country)
        }
    }

    companion object {
        private val COUNTRY_DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<Country>(){
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}