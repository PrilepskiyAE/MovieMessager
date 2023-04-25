package com.prilepskiy.moviemessager.ui.fragment.message

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.prilepskiy.moviemessager.databinding.FragmentMessageBinding
import com.prilepskiy.moviemessager.ui.adapter.MessageAdapter
import com.prilepskiy.moviemessager.ui.base.FragmentBaseNCMVVM
import com.prilepskiy.moviemessager.ui.base.viewBinding
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageFragment : FragmentBaseNCMVVM<MessageViewModel, FragmentMessageBinding>() {
    override val binding: FragmentMessageBinding by viewBinding()
    override val viewModel: MessageViewModel by viewModels()
    lateinit var messageAdapter:MessageAdapter
   val args: MessageFragmentArgs by navArgs()


    override fun onEach() {
        messageAdapter= MessageAdapter(args.user)
        binding.rvItemsList.apply {
            context?.let {
                layoutManager = LinearLayoutManager(it)
                adapter = messageAdapter
            }
        }
        onEach(viewModel.listMessage){
            Log.d("TAG999", "onEach: ===========> ${it?.size} ")
           // messageAdapter.submitList(it)
        }
    }
    override fun onView() {
        viewModel.GetListMessage(args.user.uid,{
            Log.d("TAG999", "onViewClick: ${it.size} ")
            messageAdapter.submitList(it)
        }) {
            showErrorDialog("error", it, true, { })
        }

}
    override fun onViewClick() {
    binding.btSend.setOnClickListener {
        viewModel.sendMessage(
            to=args.user,
            message = binding.etMessage.text.toString())

        viewModel.GetListMessage(args.user.uid,{
            Log.d("TAG999", "onViewClick: ${it.size} ")
            messageAdapter.submitList(it)
        }) {
            showErrorDialog("error", it, true, { })
        }
        binding.etMessage.setText("")
    }

    }






}