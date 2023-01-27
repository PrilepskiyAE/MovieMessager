package com.example.moviemessager.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.moviemessager.databinding.FragmentDashboardBinding
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : FragmentBaseNCMVVM<DashboardViewModel, FragmentDashboardBinding>() {
    override val binding: FragmentDashboardBinding by viewBinding()
    override val viewModel: DashboardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovie()
    }

    override fun onView() {
        super.onView()
        viewModel.getMovie()
    }

    override fun onEach() {
        onEach(viewModel.state) {
            if (it.isLoading)
                Log.d("TAG_LOADING", "onEach: $it")
            else {
                lifecycleScope.launchWhenStarted {
                    it.movieList?.let {
                        Log.d("TAG_LOADING", "onEach: $it")
                    }
                }
            }
        }
    }

}