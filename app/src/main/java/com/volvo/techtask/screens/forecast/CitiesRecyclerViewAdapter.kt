/// Author(s): Mohsen Emami
/// Modified Date: 10/Feb/2022


package com.volvo.techtask.screens.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.volvo.techtask.R
import com.volvo.techtask.models.city.City

/**
 * Adapter for Cities screen recyclerview.
 */
class CitiesRecyclerViewAdapter(val context: Context, list: ArrayList<City>):
    RecyclerView.Adapter<CitiesRecyclerViewAdapter.CitiesFragViewHolder>(), Filterable {

    var mItemList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesFragViewHolder {
        return CitiesFragViewHolder(LayoutInflater.from(context).inflate(R.layout.city_list_view_item,parent,false))
    }

    fun updateListItems(updatedList: ArrayList<City>){
        mItemList.clear()
        mItemList = updatedList
        notifyDataSetChanged()
    }

    fun addItem(item: City){
        mItemList.add(item)
    }

    override fun getItemCount(): Int {
        return mItemList.size
    }

    override fun onBindViewHolder(holder: CitiesFragViewHolder, position: Int) {
        val city: City = mItemList[position]
        holder.cityName.text = city.title
        holder.cityLatLong.text = "Coordinate:  ${city.latLong}"
        holder.itemView.setOnClickListener() { view ->
            val bundle = bundleOf("woeId" to city.woeId)
            view.findNavController().navigate(R.id.action_frgCities_to_frgWeatherForecast, bundle)
        }
    }

    inner class CitiesFragViewHolder(item: View): RecyclerView.ViewHolder(item){
        val cityName : TextView = item.findViewById(R.id.cityName)
        val cityLatLong : TextView = item.findViewById(R.id.cityLatLong)
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented, we can add filter feature later")
    }
}