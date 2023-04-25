package com.prilepskiy.moviemessager.ui.fragment.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.prilepskiy.moviemessager.R
import com.prilepskiy.moviemessager.databinding.FragmentHomeBinding
import com.prilepskiy.moviemessager.ui.adapter.MovieFavoriteAdapter
import com.prilepskiy.moviemessager.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.moviemessager.ui.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : FragmentBaseNCMVVM<HomeViewModel, FragmentHomeBinding>() {
    override val binding: FragmentHomeBinding by viewBinding()
    override val viewModel: HomeViewModel by viewModels()
    private val movieFavoriteAdapter = MovieFavoriteAdapter {
        navigateFragment(
            HomeFragmentDirections.actionNavigationHomeToMoviDetailFragment(it)
        )
    }


    private fun setAdapter() {
        binding.rvItemsListFavorite
            .apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = movieFavoriteAdapter
        }

    }


    override fun onEach() {
        onEach(viewModel.email) {
            binding.tvName.text = it
        }
        onEach(viewModel.listFavoriteMovie) {
           if (!it.isNullOrEmpty()){
            binding.tvFavoriteLabel.text="Favorite"
           }
            movieFavoriteAdapter.submitList(it)

        }
//        onEach(viewModel.movieList){
//            it.also {
//                if (binding.rvItemsListTop.adapter == null)
//                    binding.rvItemsListTop.apply {
//                        layoutManager = GridLayoutManager(context, 4)
//                        adapter = movieAdapter
//
//                    }
//
//            }
//            lifecycleScope.launch {
//                if (it != null) {
//
//                    movieAdapter.submitData(it)
//
//                }else{
//                    Toast.makeText(requireContext(), "connect server error", Toast.LENGTH_LONG).show()}
//            }
//
//
//
//        }
    }

    override fun onView() {
        if(viewModel.listFavoriteMovie.value?.size==0 || viewModel.listFavoriteMovie.value==null){
            binding.tvFavoriteLabel.text="No items favorite"
        }
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