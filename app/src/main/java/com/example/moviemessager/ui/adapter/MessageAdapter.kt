package com.example.moviemessager.ui.adapter

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.ui.base.BaseAdapter
import com.example.moviemessager.ui.base.BaseViewHolder

class MessageAdapter(): BaseAdapter<ViewBinding, MessageUser, BaseViewHolder<MessageUser, ViewBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MessageUser, ViewBinding> {
        TODO("Not yet implemented")
    }
}