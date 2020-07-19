package com.pdk.dicoding.kade.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdk.dicoding.kade.data.model.Event
import com.pdk.dicoding.kade.databinding.ItemEventBinding


/**
 * Created by Budi Ardianata on 16/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class EventsAdapter(
    private val events: ArrayList<Event>,
    private val clickListener: (Event, View) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        return EventsViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventsAdapter.EventsViewHolder, position: Int) =
        holder.bind(events[position], clickListener)

    fun setData(items: List<Event>) {
        events.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class EventsViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, click: (Event, View) -> Unit) {
            binding.data = event
            binding.rootCard.transitionName = event.id
            binding.rootCard.setOnClickListener { v ->
                click(event, v)
            }
        }
    }
}