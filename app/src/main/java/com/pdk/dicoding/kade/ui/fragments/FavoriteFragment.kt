package com.pdk.dicoding.kade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdk.dicoding.kade.databinding.FragmentFavoriteBinding
import com.pdk.dicoding.kade.ui.adapters.EventsAdapter
import com.pdk.dicoding.kade.ui.viewmodels.FavoriteViewModel


class FavoriteFragment : Fragment() {
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        eventsAdapter = EventsAdapter(arrayListOf()) { e, v ->
            findNavController().navigate(
                FavoriteFragmentDirections.favoriteToEventDetails(e.eventName, e),
                FragmentNavigatorExtras(
                    v to e.id
                )
            )
        }
        binding.recyclerView.adapter = eventsAdapter
        viewModel.eventFavorite.observe(viewLifecycleOwner, { events ->
            eventsAdapter.setData(events)
            if (events.isNullOrEmpty()) {
                binding.errLayout.main.visibility = View.VISIBLE
            } else {
                binding.errLayout.main.visibility = View.GONE
            }
        })
    }

}