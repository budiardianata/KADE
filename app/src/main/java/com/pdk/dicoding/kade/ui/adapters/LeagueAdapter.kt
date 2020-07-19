package com.pdk.dicoding.kade.ui.adapters

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.pdk.dicoding.kade.data.model.League
import com.pdk.dicoding.kade.databinding.ItemLeagueBinding

/**
 * Created by Budi Ardianata on 13/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class LeagueAdapter(
    private val leagues: ArrayList<League>,
    private val clickListener: (League, View) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            ItemLeagueBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) =
        holder.bind(leagues[position], clickListener)

    fun setData(items: List<League>) {
        leagues.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class LeagueViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(league: League, click: (League, View) -> Unit) {
            binding.data = league
            Glide.with(binding.root)
                .asBitmap()
                .load(league.badge)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val palette = Palette.from(resource).generate()
                        palette.darkVibrantSwatch?.let {
                            binding.rootCard.setCardBackgroundColor(it.rgb)
                            binding.leagueName.setTextColor(Color.WHITE)
                        } ?: palette.lightVibrantSwatch?.let {
                            binding.rootCard.setCardBackgroundColor(it.rgb)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
            binding.rootCard.transitionName = league.id
            binding.root.setOnClickListener { click(league, binding.rootCard) }
        }
    }
}