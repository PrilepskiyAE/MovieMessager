package com.prilepskiy.moviemessager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.prilepskiy.moviemessager.databinding.ItemFromMessageBinding
import com.prilepskiy.moviemessager.databinding.ItemToMessageBinding
import com.prilepskiy.moviemessager.domain.model.MessageUser
import com.prilepskiy.moviemessager.domain.model.UserModel
import com.prilepskiy.moviemessager.ui.base.BaseAdapter
import com.prilepskiy.moviemessager.ui.base.BaseViewHolder

class MessageAdapter(private val to: UserModel) :
    BaseAdapter<ViewBinding, MessageUser, BaseViewHolder<MessageUser, ViewBinding>>() {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (to == item.to) {
            MessageClassType.FROM.ordinal
        } else
            MessageClassType.TO.ordinal


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MessageUser, ViewBinding> {
        val binding = when (viewType) {
            MessageClassType.FROM.ordinal -> {
                ItemFromMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            MessageClassType.TO.ordinal -> {
                ItemToMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            else -> {
                throw RuntimeException("Unknown view type: $viewType")
            }
        }
        return MessageViewHolder(binding)
    }

    inner class MessageViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<MessageUser, ViewBinding>(binding) {
        override fun bind(item: MessageUser, context: Context) {
            with(binding) {
                when (this) {
                    is ItemToMessageBinding->{
                        tvMessage.text=item.message
                        userName.text=item.from.email
                    }
                    is ItemFromMessageBinding -> {
                        tvMessage.text=item.message
                        userName.text=item.from.email
                    }
                }
            }
        }


    }

    enum class MessageClassType {
        TO,
        FROM,
    }
}