package com.pdk.dicoding.kade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.pdk.dicoding.kade.databinding.FragmentEventDetailsBinding
import com.pdk.dicoding.kade.ui.viewmodels.EventDetailViewModel
import com.pdk.dicoding.kade.utils.Utils


class EventDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEventDetailsBinding
    private val args: EventDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: EventDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = Utils.materialContainerTransform(requireActivity())
        sharedElementReturnTransition = Utils.materialContainerTransform(requireActivity())
        viewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
        viewModel.getBadges(args.event.idHome, args.event.awayId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        binding.coordinator.transitionName = args.event.id
        binding.mainLayout.visibility = View.GONE
        viewModel.listBadge.observe(viewLifecycleOwner, Observer { badge ->
            args.event.homeBadge = badge[0]
            args.event.awayBadge = badge[1]
            binding.apply {
                data = args.event
                mainLayout.visibility = View.VISIBLE
                progress.visibility = View.GONE
            }
        })
    }

    private fun initToolbar() {
        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )
    }
}