package com.example.moviemessager.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.moviemessager.R
import com.example.moviemessager.databinding.ItemFromMessageBinding
import com.example.moviemessager.databinding.ItemGeneresBinding
import com.example.moviemessager.databinding.ItemGroupBinding
import com.example.moviemessager.databinding.ItemMovieBinding
import com.example.moviemessager.databinding.ItemToMessageBinding
import com.example.moviemessager.domain.model.MessageUser
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.ui.base.BaseAdapter
import com.example.moviemessager.ui.base.BaseViewHolder
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

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