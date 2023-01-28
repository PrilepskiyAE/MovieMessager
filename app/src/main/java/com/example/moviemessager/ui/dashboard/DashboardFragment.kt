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
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviemessager.data.response.GenresResponse
import com.example.moviemessager.databinding.FragmentDashboardBinding
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.home.HomeViewModel
import com.example.moviemessager.ui.pagingadapter.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : FragmentBaseNCMVVM<DashboardViewModel, FragmentDashboardBinding>() {
    override val binding: FragmentDashboardBinding by viewBinding()
    override val viewModel: DashboardViewModel by viewModels()
    private val movieAdapter=MoviePagingAdapter({})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMovie()
    }

    override fun onEach() {
        onEach(viewModel.movieList){
            it.also {
                if (binding.rvItemsList.adapter == null)
                    binding.rvItemsList.apply {
                        layoutManager = GridLayoutManager(context, 2)
                        (layoutManager as GridLayoutManager).setSpanSizeLookup(object :
                            GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                if (position > 2) return 1
                                else return 2
                            }

                        })
                        adapter = movieAdapter

                    }

            }
            lifecycleScope.launchWhenStarted {
                if (it != null) {

                    movieAdapter.submitData(it)

                }
            }



            }
        }
    }


