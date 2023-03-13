package com.example.moviemessager.ui.fragment.profile

import androidx.fragment.app.viewModels
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
        isAuth()
    }
    override fun onViewClick() {
        binding.btLogOut.setOnClickListener {
            auth.signOut()
            isAuth()
        }
    }
}