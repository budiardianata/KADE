package com.pdk.dicoding.kade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pdk.dicoding.kade.databinding.FragmentLeagueDetailDialogBinding


class LeagueDetailDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentLeagueDetailDialogBinding
    private val args: LeagueDetailDialogFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeagueDetailDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.data = args.league
    }

}