package com.prilepskiy.moviemessager.ui.fragment.userlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.prilepskiy.moviemessager.databinding.FragmentUserListBinding
import com.prilepskiy.moviemessager.ui.adapter.UsersAdapter
import com.prilepskiy.moviemessager.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.moviemessager.ui.base.viewBinding
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : FragmentBaseNCMVVM<UserListViewModel, FragmentUserListBinding>() {
    override val binding: FragmentUserListBinding by viewBinding()
    override val viewModel: UserListViewModel by viewModels()


    private val userAdapter = UsersAdapter {
        navigateFragment(UserListFragmentDirections.actionUserListFragmentToNavigationMessage(it))
    }

    private fun setAdapter() {
        binding.rvItemsList.apply {
            context?.let {
                layoutManager = LinearLayoutManager(it)
                adapter = userAdapter
            }
        }

    }

    override fun onView() {
        setAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.initListUsers({
            userAdapter.submitList(it)
        }, {
            showErrorDialog("Error Action", it, false, {  }, {

            })
        }, {

            //navigateFragment(R.id.loginFragment)
        })

    }

}