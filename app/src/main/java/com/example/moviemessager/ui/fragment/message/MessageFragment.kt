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
import com.example.moviemessager.domain.model.MessageUserFirebase
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.domain.model.UserModelFirebase
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
    override val binding: FragmentMessageBinding by viewBinding()
    override val viewModel: MessageViewModel by viewModels()
    private val database: FirebaseDatabase = Firebase.database
    lateinit var myRef: DatabaseReference
//
//    val args: MessageFragmentArgs by navArgs()
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.isAuth {
//            navigateFragment(R.id.loginFragment)
//        }
//        myRef =
//            database.getReference("FROM_${auth.currentUser?.displayName ?: "Anonimus"}_TO_${args.user.username} ")
//
//        onChangeListener(myRef)
//    }
//
//    override fun onView() {
//        binding.btSend.setOnClickListener {
//            myRef.child(myRef.push().key ?: "omnonom").setValue(
//                MessageUserFirebase(
//                    UserModelFirebase(
//                        auth.currentUser?.displayName ?: "noname",
//                        auth.currentUser?.email ?: "noEmail"
//                    ),
//                    UserModelFirebase(args.user.username, args.user.email),
//                    binding.etMessage.text.toString()
//                )
//            )
//        }
//
//
//    }
//

}