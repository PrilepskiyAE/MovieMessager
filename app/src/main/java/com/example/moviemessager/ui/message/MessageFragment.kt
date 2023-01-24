package com.example.moviemessager.ui.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.databinding.FragmentMessageBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageFragment : FragmentBaseNCMVVM<MessageViewModel, FragmentMessageBinding>() {
    override val binding:FragmentMessageBinding by viewBinding()
    override val viewModel: MessageViewModel by viewModels()
    override fun onView() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textMessage.text = it
        }
    }
}