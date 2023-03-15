package com.example.moviemessager.ui.fragment.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentProfileBinding
import com.example.moviemessager.databinding.FragmentUserListBinding
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.ui.adapter.UsersAdapter
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.profile.ProfileViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : FragmentBaseNCMVVM<UserListViewModel, FragmentUserListBinding>() {
    override val binding: FragmentUserListBinding by viewBinding()
    override val viewModel: UserListViewModel by viewModels()
    private val database: FirebaseDatabase = Firebase.database
    private val myRef = database.getReference("users")
    private var users:MutableList<UserModel> = mutableListOf()
    private val userAdapter=UsersAdapter({

    })

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


        onChangeListener(myRef)
    }
    private fun onChangeListener(dRef: DatabaseReference){
        auth= Firebase.auth

        if (auth.currentUser==null){
            Toast.makeText(requireContext(), "auth", Toast.LENGTH_SHORT).show()
            navigateFragment(R.id.loginFragment)
        }else{
            dRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        users.add(UserModel( it.child("username").value.toString(),it.child("email").value.toString()))
                    }
                    Log.d("TAG", "onChangeListener: $users")
                    Log.d("TAG", "Controlsum: ${snapshot.value.toString()}")
                    userAdapter.submitList(users)
//          binding.apply {
//              rcViewTest.append("\n")
//              rcViewTest.append("${auth.currentUser?.email?.toString()}: ${snapshot.value.toString()}")
//          }

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}