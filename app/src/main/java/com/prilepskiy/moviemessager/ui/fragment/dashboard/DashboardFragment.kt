package com.prilepskiy.moviemessager.ui.fragment.dashboard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.prilepskiy.moviemessager.databinding.FragmentDashboardBinding
import com.prilepskiy.moviemessager.ui.adapter.LoaderStateAdapter
import com.prilepskiy.moviemessager.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.moviemessager.ui.base.viewBinding
import com.prilepskiy.moviemessager.ui.dashboard.DashboardViewModel
import com.prilepskiy.moviemessager.ui.pagingadapter.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : FragmentBaseNCMVVM<DashboardViewModel, FragmentDashboardBinding>(),
    SwipeRefreshLayout.OnRefreshListener {
    override val binding: FragmentDashboardBinding by viewBinding()
    override val viewModel: DashboardViewModel by viewModels()
    private val movieAdapter = MoviePagingAdapter({
        navigateFragment(
            DashboardFragmentDirections.actionNavigationDashboardToMoviDetailFragment(it)
        )
    }, {
        viewModel.loadMovie(it)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMovie(0)

    }

    override fun onEach() {
        onEach(viewModel.movieList) {
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
                        adapter = movieAdapter.withLoadStateFooter(LoaderStateAdapter {
                            movieAdapter.retry()
                        })

                    }

            }


            lifecycleScope.launch {

                if (it != null) {
                    movieAdapter.submitData(it)
                }

            }


        }

    }

    override fun onRefresh() {
        Log.d("TAG", "onRefresh: ")
        viewModel.loadMovie(0)
        movieAdapter.retry()
        //binding.swipeRefresh.isRefreshing=false
        binding.swipeRefresh.stopRefresh()
    }

    override fun onResume() {
        super.onResume()
        setLoadingStateListener()
    }

    private fun setLoadingStateListener() {
        movieAdapter.setLoadStateListener(
            onErrorAction = {
                showErrorDialog("Error Action", it, true, { viewModel.loadMovie(0) }, {
                    Toast.makeText(requireContext(), "Error connect to server", Toast.LENGTH_SHORT)
                        .show()
                })

            },
            onSuccessAction = {
                Log.d("TAG", "setLoadingStateListener: onSuccessAction  ")
                binding.swipeRefresh.stopRefresh()
            },
            onNoDataAction = {
                Log.d("TAG", "setLoadingStateListener: onNoDataAction  ")
                Toast.makeText(requireContext(), "No Data Action", Toast.LENGTH_SHORT).show()
            }

        )
        movieAdapter.addOnLoadingStateListener()
    }

    override fun onPause() {
        super.onPause()
        movieAdapter.removeOnLoadingStateListener()
    }

}

fun SwipeRefreshLayout.stopRefresh() {
    Handler(Looper.getMainLooper()).postDelayed({
        this.isRefreshing = false
    }, 2000L)
}

