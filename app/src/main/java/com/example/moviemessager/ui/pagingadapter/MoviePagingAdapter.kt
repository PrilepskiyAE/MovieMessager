package com.example.moviemessager.ui.pagingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide


import com.example.moviemessager.databinding.ItemMovieBinding
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.ui.base.BasePagingAdapter
import com.example.moviemessager.ui.base.BaseViewHolder

class MoviePagingAdapter(
    private val onClickButtonClicked: (MovieUImodel.MovieModel)-> Unit):
    BasePagingAdapter<ViewBinding, MovieUImodel, BaseViewHolder<MovieUImodel, ViewBinding>>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieUImodel, ViewBinding> {
        return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    inner class MovieViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<MovieUImodel, ViewBinding>(binding) {
        override fun bind(item: MovieUImodel, context: Context) {
            with(binding) {
                when (this) {
                    is ItemMovieBinding -> {
                        if (item is MovieUImodel.MovieModel){
                        filmName.text=item.title
                        Glide.with(itemView)
                            .load("https://image.tmdb.org/t/p/w500"+item.poster_path)
                            .into(filmCover)
                    }}
                    else -> {}
                }
            }
        }


    }



//    override fun getItemViewType(position: Int): Int {
//    val item = getItem(position)
//    return when (item) {
//        is MovieUImodel.MovieModel -> MovieClassType.MOVIE.ordinal
//
//
//        is MovieUImodel.Title -> MovieClassType.TITLE.ordinal
//
//        is MovieUImodel.Genre -> MovieClassType.GENERALS.ordinal
//
//        else -> {
//            MovieClassType.NULL.ordinal
//        }
//    }
//}
    enum class MovieClassType {
        MOVIE,
        TITLE,
        GENERALS,
        NULL
    }
}