package com.example.moviemessager.ui.fragment.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentHomeBinding
import com.example.moviemessager.databinding.FragmentMessageBinding
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import com.example.moviemessager.ui.fragment.home.HomeViewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        onChangeListener(myRef)
    }
    override fun onView() {
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textMessage.text = it
        }

        binding.btSend.setOnClickListener {
            myRef.setValue(binding.etMessage.text.toString())
        }


    }
private fun onChangeListener(dRef:DatabaseReference){
    dRef.addValueEventListener(object :ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
          binding.apply {
              rcViewTest.append("\n")
              rcViewTest.append("Alexey: ${snapshot.value.toString()}")
          }
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }

    })
}
}