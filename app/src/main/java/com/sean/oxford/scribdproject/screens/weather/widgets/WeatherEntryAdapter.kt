package com.sean.oxford.scribdproject.screens.weather.widgets

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.sean.oxford.scribdproject.model.WeatherEntry
import com.sean.oxford.scribdproject.screens.weather.widgets.WeatherEntryAdapter.WeatherEntryViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class WeatherEntryAdapter(val callback: WeatherEntryAdapter.Callback) :
    RecyclerView.Adapter<WeatherEntryViewHolder>() {


    interface Callback {
        fun onClick()
    }

    class WeatherEntryViewHolder(val weatherEntryRowView: WeatherEntryRowView) :
        RecyclerView.ViewHolder(weatherEntryRowView)

    private val diffCallback = object : DiffUtil.ItemCallback<WeatherEntry>() {
        override fun areItemsTheSame(oldItem: WeatherEntry, newItem: WeatherEntry): Boolean =
            oldItem.dt == newItem.dt

        override fun areContentsTheSame(oldItem: WeatherEntry, newItem: WeatherEntry): Boolean =
            oldItem == newItem
    }

    private val differ =
        AsyncListDiffer(
            RecyclerChangeCallback(this),
            AsyncDifferConfig.Builder(diffCallback).build()
        )

    fun submitList(weatherEntries: List<WeatherEntry>) {
        differ.submitList(weatherEntries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherEntryViewHolder =
        WeatherEntryViewHolder(
            WeatherEntryRowView(
                parent.context,
                object : WeatherEntryRowView.DoThinger {
                    override fun thing() {}
                })
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: WeatherEntryViewHolder, position: Int) {
        val weatherEntry = differ.currentList[position]
        holder.weatherEntryRowView.setWeatherEntry(weatherEntry)
        holder.weatherEntryRowView.setOnClickListener { callback.onClick() }
    }

    internal inner class RecyclerChangeCallback(private val adapter: WeatherEntryAdapter) :
        ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }


}