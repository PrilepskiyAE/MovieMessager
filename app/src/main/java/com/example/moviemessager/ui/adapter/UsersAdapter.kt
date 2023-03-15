package com.example.moviemessager.ui.adapter



import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.moviemessager.databinding.ItemUserBinding
import com.example.moviemessager.domain.model.UserModel
import com.example.moviemessager.ui.base.BaseAdapter
import com.example.moviemessager.ui.base.BaseViewHolder

class UsersAdapter(private val click: (UserModel) -> Unit): BaseAdapter<ViewBinding, UserModel, BaseViewHolder<UserModel, ViewBinding>>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<UserModel, ViewBinding> {
        return UsersViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))
    }
    inner class UsersViewHolder(
        private val binding: ItemUserBinding
    ) : BaseViewHolder<UserModel, ViewBinding>(binding){
        override fun bind(item: UserModel, context: Context) {
           binding.userName.text=item.email?:item.username
            binding.cardView.setOnClickListener {
                click(item)
            }
        }

    }
}