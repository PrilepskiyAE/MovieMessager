package com.example.moviemessager.ui.fragment.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.ui.adapter.MovieFavoriteAdapter
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.dashboard.DashboardFragmentDirections
import com.example.moviemessager.ui.pagingadapter.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : FragmentBaseNCMVVM<HomeViewModel, FragmentHomeBinding>() {
    override val binding: FragmentHomeBinding by viewBinding()
    override val viewModel: HomeViewModel by viewModels()
    private val movieFavoriteAdapter = MovieFavoriteAdapter {
        navigateFragment(
            HomeFragmentDirections.actionNavigationHomeToMoviDetailFragment(it)
        )
    }

    private val movieAdapter= MoviePagingAdapter({navigateFragment( HomeFragmentDirections.actionNavigationHomeToMoviDetailFragment(it))},{

    })
    private fun setAdapter() {
        binding.rvItemsListFavorite
            .apply {
                layoutManager = GridLayoutManager(context, 4)
                adapter = movieFavoriteAdapter
        }

    }


    override fun onEach() {
        onEach(viewModel.email) {
            binding.tvName.text = it
        }
        onEach(viewModel.listFavoriteMovie) {

            movieFavoriteAdapter.submitList(it)

        }
        onEach(viewModel.movieList){
            it.also {
                if (binding.rvItemsListTop.adapter == null)
                    binding.rvItemsListTop.apply {
                        layoutManager = GridLayoutManager(context, 4)
                        adapter = movieAdapter

                    }

            }
            lifecycleScope.launch {
                if (it != null) {

                    movieAdapter.submitData(it)

                }else{
                    Toast.makeText(requireContext(), "connect server error", Toast.LENGTH_LONG).show()}
            }



        }
    }

    override fun onView() {
        viewModel.getEmail()
        viewModel.isAuth {
            navigateFragment(R.id.loginFragment)
        }
        viewModel.getFavorite()
        //viewModel.loadMovie(0)
        setAdapter()
    }

    override fun onViewClick() {
        binding.imbExit.setOnClickListener {
            viewModel.logout {
                Toast.makeText(requireContext(), "Exit", Toast.LENGTH_SHORT).show()
                viewModel.isAuth {
                    navigateFragment(R.id.loginFragment)
                }
            }
        }
    }


}