package com.example.moviemessager.ui.pagingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.moviemessager.R

import com.example.moviemessager.databinding.ItemMovieBinding
import com.example.moviemessager.domain.model.MovieModel
import com.example.moviemessager.ui.base.BasePagingAdapter
import com.example.moviemessager.ui.base.BaseViewHolder

class MoviePagingAdapter(
    private val onClickButtonClicked: (MovieModel)-> Unit):
    BasePagingAdapter<ViewBinding, MovieModel, BaseViewHolder<MovieModel, ViewBinding>>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieModel, ViewBinding> {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    inner class MovieViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<MovieModel, ViewBinding>(binding) {
        override fun bind(item: MovieModel, context: Context) {
            with(binding) {
                when (this) {
                    is ItemMovieBinding -> {
                        filmName.text=item.title
                        Glide.with(itemView)
                            .load("https://image.tmdb.org/t/p/w500"+item.poster_path)
                            .into(filmCover)
                    }
                    else -> {}
                }
            }
        }
        override fun onItemClick(item: MovieModel) {
            onItemClick(item)
            onClickButtonClicked(item)
        }
    }
}