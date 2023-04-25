package com.prilepskiy.moviemessager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.prilepskiy.moviemessager.databinding.ItemMovieFavoriteBinding
import com.prilepskiy.moviemessager.domain.model.MovieUImodel
import com.prilepskiy.moviemessager.ui.base.BaseAdapter
import com.prilepskiy.moviemessager.ui.base.BaseViewHolder

class MovieFavoriteAdapter(
    private val onClickButtonClicked: (MovieUImodel.MovieModel) -> Unit
) :
    BaseAdapter<ViewBinding, MovieUImodel.MovieModel, BaseViewHolder<MovieUImodel.MovieModel, ViewBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieUImodel.MovieModel, ViewBinding> {
        return MovieViewHolder(
            ItemMovieFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieFavoriteBinding
    ) : BaseViewHolder<MovieUImodel.MovieModel, ViewBinding>(binding) {
        override fun bind(item: MovieUImodel.MovieModel, context: Context) {
            with(binding){
            filmName.text=item.title
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500"+item.poster_path)
                .into(filmCover)

            filmCover.setOnClickListener {

                onClickButtonClicked(item)
            }
        }
    }}
}