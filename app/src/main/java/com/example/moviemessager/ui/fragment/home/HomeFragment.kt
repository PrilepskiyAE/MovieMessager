package com.example.moviemessager.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : FragmentBaseNCMVVM<HomeViewModel, FragmentHomeBinding>() {
    override val binding: FragmentHomeBinding by viewBinding()
    override val viewModel: HomeViewModel by viewModels()

    override fun onView() {// viewModel.test()
    isAuth()

    }


}