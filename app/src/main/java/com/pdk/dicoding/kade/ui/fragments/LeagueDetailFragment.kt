package com.pdk.dicoding.kade.ui.fragments

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.palette.graphics.Palette
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayoutMediator
import com.pdk.dicoding.kade.R
import com.pdk.dicoding.kade.databinding.FragmentLeagueDetailBinding
import com.pdk.dicoding.kade.utils.Utils


class LeagueDetailFragment : Fragment() {
    private lateinit var binding: FragmentLeagueDetailBinding
    private val args: LeagueDetailFragmentArgs by navArgs()
    private lateinit var pagerAdapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = Utils.materialContainerTransform(requireActivity())
        sharedElementReturnTransition = Utils.materialContainerTransform(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeagueDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbar.background
        binding.coordinator.transitionName = args.league.id
        initToolbar()
        binding.data = args.league


        initTab()

        binding.fab.setOnClickListener {
            findNavController().navigate(
                LeagueDetailFragmentDirections.leagueDetailToLeagueDialog(
                    args.league
                )
            )

        }
    }

    private fun initToolbar() {
        binding.collapsingToolbar.setupWithNavController(
            binding.toolbar,
            findNavController(),
            AppBarConfiguration(findNavController().graph)
        )
        Glide.with(requireContext())
            .asBitmap()
            .load(args.league.badge)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    val palette = Palette.from(resource).generate()
                    palette.darkVibrantSwatch?.let {
                        binding.collapsingToolbar.setBackgroundColor(it.rgb)
                        binding.collapsingToolbar.setContentScrimColor(it.rgb)
                        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
                        binding.collapsingToolbar.setExpandedTitleColor(Color.WHITE)
                    } ?: palette.lightVibrantSwatch?.let {
                        binding.collapsingToolbar.setContentScrimColor(it.rgb)
                        binding.collapsingToolbar.setBackgroundColor(it.rgb)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun initTab() {
        val tabList = arrayOf(
            resources.getString(R.string.previous_match),
            resources.getString(R.string.next_match)
        )
        pagerAdapter = PagerAdapter(tabList, args.league.id, this)
        binding.pager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    inner class PagerAdapter(
        private val tabList: Array<String>,
        private val username: String,
        fragment: Fragment
    ) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = tabList.size

        override fun createFragment(position: Int): Fragment =
            EventListFragment.newInstance(username, tabList[position])
    }
}