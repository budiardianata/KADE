package com.pdk.dicoding.kade.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.data.model.League
import com.pdk.dicoding.kade.databinding.FragmentHomeBinding
import com.pdk.dicoding.kade.ui.adapters.LeagueAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeAdapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        homeAdapter = LeagueAdapter(dataLeague()) { league ->
            findNavController().navigate(
                HomeFragmentDirections.homeToDetailLeague(league.name, league.id)
            )
        }
        binding.recyclerHome.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = homeAdapter
        }
    }

    private fun dataLeague(): List<League> {
        val leagues: MutableList<League> = mutableListOf()
        val ids = resources.getIntArray(R.array.ids)
        val names = resources.getStringArray(R.array.name)
        val images = resources.getStringArray(R.array.image)
        for (i in ids.indices) {
            leagues.add(
                League(ids[i], names[i], images[i])
            )
        }
        return leagues
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu_top, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(
            item
        )
    }
}