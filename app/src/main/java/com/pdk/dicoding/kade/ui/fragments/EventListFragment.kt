package com.pdk.dicoding.kade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdk.bfaadicoding.submission.utils.Status
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.databinding.FragmentEventListBinding
import com.pdk.dicoding.kade.ui.adapters.EventsAdapter
import com.pdk.dicoding.kade.ui.viewmodels.PrevNextViewModel
import com.pdk.dicoding.kade.utils.EventType


private const val TYPE = "type"
private const val LEAGUE_ID = "username"

class EventListFragment : Fragment() {
    private var leagueId: String? = null
    private var type: String? = null
    private lateinit var viewModel: PrevNextViewModel
    private lateinit var eventsAdapter: EventsAdapter
    private lateinit var binding: FragmentEventListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            leagueId = it.getString(LEAGUE_ID)
            type = it.getString(TYPE)
        }
        viewModel = ViewModelProvider(this).get(PrevNextViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventsAdapter = EventsAdapter(arrayListOf()) { e, v ->
            findNavController().navigate(
                LeagueDetailFragmentDirections.leagueDetailToEventDetails(
                    e.eventName,
                    e
                ),
                FragmentNavigatorExtras(
                    v to e.id
                )
            )
        }

        binding.recyclerEvent.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = eventsAdapter
        }

        when (type) {
            resources.getString(R.string.next_match) -> {
                viewModel.setParam(leagueId, EventType.NEXT)

            }
            resources.getString(R.string.previous_match) -> {
                viewModel.setParam(leagueId, EventType.PREVIOUS)
            }
            else -> {
                showError(null)
            }
        }

        observeData()
    }

    private fun observeData() {
        viewModel.dataEvent.observe(viewLifecycleOwner, Observer {
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
        binding.errLayout.main.visibility = View.VISIBLE
        binding.errLayout.emptyText.text = message ?: resources.getString(R.string.not_found)
        binding.progress.visibility = View.GONE
        binding.recyclerEvent.visibility = View.GONE
    }

    private fun showSuccess() {
        binding.errLayout.main.visibility = View.GONE
        binding.progress.visibility = View.GONE
        binding.recyclerEvent.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.errLayout.main.visibility = View.GONE
        binding.progress.visibility = View.VISIBLE
        binding.recyclerEvent.visibility = View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String, type: String) =
            EventListFragment().apply {
                arguments = Bundle().apply {
                    putString(LEAGUE_ID, id)
                    putString(TYPE, type)
                }
            }
    }
}