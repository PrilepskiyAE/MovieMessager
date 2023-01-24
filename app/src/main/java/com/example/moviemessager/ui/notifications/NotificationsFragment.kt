package com.example.moviemessager.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviemessager.databinding.FragmentDashboardBinding
import com.example.moviemessager.databinding.FragmentNotificationsBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment: FragmentBaseNCMVVM<NotificationsViewModel, FragmentNotificationsBinding>() {
    override val binding: FragmentNotificationsBinding by viewBinding()
    override val viewModel: NotificationsViewModel by viewModels()

    override fun onView() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textNotifications.text=it
        }
    }
}