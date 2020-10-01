package com.pdk.dicoding.kade.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.databinding.FragmentEventBinding
import com.pdk.dicoding.kade.ui.adapters.EventsAdapter
import com.pdk.dicoding.kade.ui.viewmodels.EventViewModel
import com.pdk.dicoding.kade.utils.Status


class EventFragment : Fragment() {

    private lateinit var binding: FragmentEventBinding
    private lateinit var eventsAdapter: EventsAdapter
    private val viewModel: EventViewModel by navGraphViewModels(R.id.my_navigation)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.errLayout.emptyText.text = resources.getString(R.string.search_match_hint)
        eventsAdapter = EventsAdapter(arrayListOf()) { e, v ->
            findNavController().navigate(
                EventFragmentDirections.eventToEventDetails(e.eventName, e),
                FragmentNavigatorExtras(
                    v to e.id
                )
            )
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = eventsAdapter
        }
        viewModel.searchResult.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        showSuccess()
                        eventsAdapter.run { setData(it.data) }
                    } else {
                        showError(resources.getString(R.string.not_found))
                    }
                }

                Status.LOADING -> {
                    showLoading()
                }

                Status.ERROR -> {
                    showError(it.message)
                }
            }
        })
    }

    private fun showError(message: String?) {
        binding.apply {
            errLayout.main.visibility = View.VISIBLE
            errLayout.emptyText.text =
                if (message.isNullOrEmpty()) resources.getString(R.string.not_found) else message
            progress.visibility = View.GONE
            recyclerView.visibility = View.GONE
        }
    }

    private fun showSuccess() {
        binding.apply {
            errLayout.main.visibility = View.GONE
            progress.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.apply {
            errLayout.main.visibility = View.GONE
            progress.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.searchView).actionView as SearchView
        searchView.apply {
            isIconified = false
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            queryHint = resources.getString(R.string.search_match_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.query.value = query
                    this@apply.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        viewModel.query.observe(viewLifecycleOwner, {
            searchView.setQuery(it, false)
            searchView.clearFocus()
        })

    }
}