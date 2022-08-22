package com.fidelsoft.adroidpaginglib.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fidelsoft.adroidpaginglib.R
import com.fidelsoft.adroidpaginglib.models.Country

class CountryViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val TAG: String = "CountryViewHolder"

    private val countryTitle = view.findViewById<TextView>(R.id.textViewCountryName)
    private val countryDesc = view.findViewById<TextView>(R.id.textViewPopulation)

    fun bind(country: Country){
        val title = "${country.name} - ${country.shortCode}"
        val desc = "Population: ${country.population}"
        countryTitle.text = title
        countryDesc.text = desc
    }

    companion object {
        fun create(parent: ViewGroup): CountryViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.countries_rv_layout,parent, false)
            return CountryViewHolder(view)
        }
    }
}