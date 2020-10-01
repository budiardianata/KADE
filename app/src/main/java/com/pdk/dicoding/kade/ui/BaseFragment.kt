package com.pdk.dicoding.kade.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * Created by Budi Ardianata on 30/07/2020.
 * Project: Logo Maker App
 * Email: budiardianata@windowslive.com
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected var dataBinding: VB? = null

    protected abstract val viewModel: VM

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        dataBinding!!.lifecycleOwner = viewLifecycleOwner
        return dataBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /*🔥 Without nullifying dataBinding ViewPager2 gets data binding related MEMORY LEAKS*/
        dataBinding = null
    }
}