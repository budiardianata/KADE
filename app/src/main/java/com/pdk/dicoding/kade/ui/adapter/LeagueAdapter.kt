package com.pdk.dicoding.kade.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.model.League
import com.pdk.dicoding.kade.ui.adapter.itemLayout.ItemLeagueLayout
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find


/**
 * Created by Budi Ardianata on 13/07/2020.
 * Project: KADE
 * Email: budiardianata@windowslive.com
 */
class LeagueAdapter(private val leagues :List<League>, private val clickListener: (League) -> Unit) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>()  {
    class LeagueViewHolder (override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(league: League, clickListener:(League) -> Unit) {
            val name = itemView.find<TextView>(R.id.name)
            val imageView = itemView.find<ImageView>(R.id.image)
            name.text = league.name
            imageView.setImageResource(league.image)
            itemView.setOnClickListener{
                clickListener(league)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            ItemLeagueLayout()
                .createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(leagues[position], clickListener)
    }
}