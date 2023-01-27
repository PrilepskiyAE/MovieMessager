package com.example.moviemessager.ui.base

import android.annotation.SuppressLint
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.example.moviemessager.core.DiffUtilModel

abstract class BasePagingAdapter<ItemViewBinding : ViewBinding, Item : DiffUtilModel<*>, ViewHolder : BaseViewHolder<Item, ItemViewBinding>> :
    PagingDataAdapter<Item, ViewHolder>(EventsDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            getItem(position)?.let { item ->
                bind(item, itemView.context)
                itemView.setOnClickListener {
                    if (position <= itemCount - 1)
                        onItemClick(item)
                }
            }
        }
    }

    private var loadStateListener: ((CombinedLoadStates) -> Unit)? = null

    fun setLoadStateListener(
        onErrorAction: (error: String) -> Unit,
        onSuccessAction: () -> Unit,
        onNoDataAction: () -> Unit
    ) {
        loadStateListener = {
            if (it.append.endOfPaginationReached && it.refresh is LoadState.NotLoading && this.itemCount < 1) {
                onNoDataAction()
            }

            val errorState = when {
                it.append is LoadState.Error -> it.append as LoadState.Error
                it.prepend is LoadState.Error -> it.prepend as LoadState.Error
                it.refresh is LoadState.Error -> it.refresh as LoadState.Error
                else -> null
            }

            if (errorState != null){
                onErrorAction(errorState.error.toString())
            } else if (it.refresh is LoadState.NotLoading && it.append is LoadState.NotLoading && it.prepend is LoadState.NotLoading) {
                onSuccessAction()
            }
        }
    }

    fun addOnLoadingStateListener() {
        loadStateListener?.let { this.addLoadStateListener(it) }
    }

    fun removeOnLoadingStateListener() {
        loadStateListener?.let { this.removeLoadStateListener(it) }
    }
    class EventsDiffCallback<Item : DiffUtilModel<*>> : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            (oldItem as DiffUtilModel<*>).id == (newItem as DiffUtilModel<*>).id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
            oldItem == newItem
    }
}