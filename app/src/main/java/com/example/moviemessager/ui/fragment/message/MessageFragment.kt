package com.example.moviemessager.ui.fragment.message

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.databinding.FragmentMessageBinding
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.home.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageFragment : FragmentBaseNCMVVM<MessageViewModel, FragmentMessageBinding>() {
    override val binding:FragmentMessageBinding by viewBinding()
    override val viewModel: MessageViewModel by viewModels()
    private val database:FirebaseDatabase =Firebase.database
    private val myRef = database.getReference("message")
    private val myRef2 = database.getReference("users")
    private var users:MutableList<UserModel> = mutableListOf()
    val args:MessageFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        onChangeListener(myRef2)
    }
    override fun onView() {
        val args3=args.user
        Log.d("TAG99", "onView: $args3 ")
        binding.btSend.setOnClickListener {
            myRef.child(myRef.push().key?:"omnonom").setValue(MessageUser(UserModel(auth.currentUser?.displayName?:"noname",auth.currentUser?.email?:"noEmail"),UserModel("frend","frend@test.com"),binding.etMessage.text.toString()))
        }


    }
private fun onChangeListener(dRef:DatabaseReference){
    auth= Firebase.auth

    if (auth.currentUser==null){
        Toast.makeText(requireContext(), "auth", Toast.LENGTH_SHORT).show()
        navigateFragment(R.id.loginFragment)
    }else{
    dRef.addValueEventListener(object :ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            snapshot.children.forEach {
                users.add(UserModel( it.child("username").value.toString(),it.child("email").value.toString()))
            }
            Log.d("TAG", "onChangeListener: $users")
            Log.d("TAG", "Controlsum: ${snapshot.value.toString()}")
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