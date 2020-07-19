package com.pdk.dicoding.kade.ui.fragments

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isEmpty
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pdk.bfaadicoding.submission.utils.Status
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.databinding.FragmentLeagueBinding
import com.pdk.dicoding.kade.ui.adapters.LeagueAdapter
import com.pdk.dicoding.kade.ui.viewmodels.LeagueViewModel
import com.pdk.dicoding.kade.utils.Utils


class LeagueFragment : Fragment() {

    private lateinit var binding: FragmentLeagueBinding

    private lateinit var homeAdapter: LeagueAdapter

    private var country: Array<String> = emptyArray()

    private val viewModel: LeagueViewModel by navGraphViewModels(R.id.my_navigation)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeagueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel.listCountry.observe(viewLifecycleOwner, Observer {
            country = it
        })
        initSearchCountry()
        homeAdapter = LeagueAdapter(arrayListOf()) { league, v ->
            findNavController().navigate(
                LeagueFragmentDirections.leagueToLeagueDetail(league.leagueName, league),
                FragmentNavigatorExtras(
                    v to league.id
                )
            )
        }

        binding.recyclerHome.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = homeAdapter
        }

        observeLeagueData()
    }

    private fun initSearchCountry() {
        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(android.R.id.text1)
        val cursorAdapter = SimpleCursorAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        val clearButton: ImageView =
            binding.countrySearch.findViewById(androidx.appcompat.R.id.search_close_btn)
        clearButton.setOnClickListener {
            viewModel.country.value = Utils.DEFAULT_COUNTRY
            binding.countrySearch.setQuery("", false)
        }
        binding.countrySearch.apply {
            suggestionsAdapter = cursorAdapter
            setOnCloseListener {
                Toast.makeText(context, "closed", Toast.LENGTH_SHORT)
                    .show()
                true
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    query?.let {
                        country.forEachIndexed { _, suggestion ->
                            if (!suggestion.contains(query, true))
                                Snackbar.make(
                                    binding.root,
                                    resources.getString(R.string.not_valid, query),
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                        }
                    }
                    return false
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    val cursor =
                        MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                    query?.let {
                        if (isEmpty()) {
                            viewModel.country.value = Utils.DEFAULT_COUNTRY
                        } else {
                            country.forEachIndexed { index, suggestion ->
                                if (suggestion.contains(query, true))
                                    cursor.addRow(arrayOf(index, suggestion))
                            }
                        }

                    }
                    cursorAdapter.changeCursor(cursor)
                    return true
                }
            })
            setOnSuggestionListener(object : SearchView.OnSuggestionListener {
                override fun onSuggestionSelect(position: Int): Boolean {
                    return false
                }

                override fun onSuggestionClick(position: Int): Boolean {
                    val cursor = this@apply.suggestionsAdapter.getItem(position) as Cursor
                    val selection =
                        cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                    this@apply.setQuery(selection, false)
                    viewModel.country.value = selection
                    clearFocus()
                    return true
                }
            })
        }
    }

    private fun observeLeagueData() {
        viewModel.listLeague.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { league ->
                            homeAdapter.setData(league)
                            showSuccess()
                        }
                    }

                    Status.LOADING -> {
                        showLoading()
                    }

                    Status.ERROR -> {
                        showError(it.message)
                    }
                }
            }
        })
    }

    private fun showError(message: String?) {

        binding.errLayout.main.visibility = View.VISIBLE
        binding.errLayout.emptyText.text =
            if (message.isNullOrEmpty()) resources.getString(R.string.not_found) else message
        binding.progress.visibility = View.GONE
        binding.recyclerHome.visibility = View.GONE
    }

    private fun showSuccess() {
        binding.errLayout.main.visibility = View.GONE
        binding.progress.visibility = View.GONE
        binding.recyclerHome.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.errLayout.main.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
        binding.recyclerHome.visibility = View.GONE
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

    override fun onResume() {
        super.onResume()
        viewModel.country.observe(this, Observer {
            binding.countrySearch.setQuery(if (it == Utils.DEFAULT_COUNTRY) "" else it, false)
        })
    }
}