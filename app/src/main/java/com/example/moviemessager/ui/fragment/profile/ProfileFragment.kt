package com.example.moviemessager.ui.fragment.profile

import androidx.fragment.app.viewModels
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.databinding.FragmentProfileBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : FragmentBaseNCMVVM<ProfileViewModel, FragmentProfileBinding>() {
    override val binding: FragmentProfileBinding by viewBinding()
    override val viewModel: ProfileViewModel by viewModels()
    override fun onView() {
        viewModel.isAuth {
            navigateFragment(R.id.loginFragment)
        }
    }
    override fun onViewClick() {
        binding.btLogOut.setOnClickListener {
          viewModel.logout {
              navigateFragment(R.id.loginFragment)
          }

        }
    }
}