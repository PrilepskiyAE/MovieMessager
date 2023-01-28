package com.example.moviemessager.ui.pagingadapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.moviemessager.R
import com.example.moviemessager.databinding.ItemGeneresBinding
import com.example.moviemessager.databinding.ItemGroupBinding


import com.example.moviemessager.databinding.ItemMovieBinding
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.ui.base.BasePagingAdapter
import com.example.moviemessager.ui.base.BaseViewHolder
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MoviePagingAdapter(
    private val onClickButtonClicked: (MovieUImodel.MovieModel)-> Unit):
    BasePagingAdapter<ViewBinding, MovieUImodel, BaseViewHolder<MovieUImodel, ViewBinding>>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MovieUImodel, ViewBinding> {
        val binding = when(viewType) {
            MovieClassType.MOVIE.ordinal ->{
               ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            MovieClassType.TITLE.ordinal ->{
                ItemGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }
            MovieClassType.GENERALS.ordinal->{
                ItemGeneresBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            }

            else -> {throw RuntimeException("Unknown view type: $viewType")}
        }
        return MovieViewHolder(binding)

    }

    inner class MovieViewHolder(
        private val binding: ViewBinding
    ) : BaseViewHolder<MovieUImodel, ViewBinding>(binding) {
        override fun bind(item: MovieUImodel, context: Context) {
            with(binding) {
                when (this) {
                    is ItemMovieBinding -> {
                        when(item){
                            is MovieUImodel.MovieModel ->{
                        filmName.text=item.title
                        Glide.with(itemView)
                            .load("https://image.tmdb.org/t/p/w500"+item.poster_path)
                            .into(filmCover)
                    }
                            else -> {}
                        }}
                    is ItemGroupBinding->{ when(item){
                        is MovieUImodel.Title ->{
                            tvTitle.text=item.title
                        }
                        else -> {throw RuntimeException("Unknown binding: ${item.javaClass}")}
                    }}
                    is ItemGeneresBinding->{
                        when(item){
                            is MovieUImodel.Genre ->{
                                itemView.findViewById<ChipGroup>(R.id.chipGpRow).removeAllViews()
                                for (i in item.list_genres) {
                                    val chip = Chip(itemView.context).apply {
                                        text = i.key
                                        isCheckable = true
                                        isCheckedIconVisible = false

                                    }
                                    itemView.findViewById<ChipGroup>(R.id.chipGpRow).addView(chip)
                                    chip.setOnCheckedChangeListener{
                                            _,isChecked ->
                                        if (isChecked) {
                                            Log.d("type999",chip.text.toString())
                                        } else{
                                            Log.d("type999","genrse")
                                        }
                                    }
                                }
                            }
                            else -> {throw RuntimeException("Unknown binding: ${item.javaClass}")}
                        }
                    }
                    else -> {throw RuntimeException("Unknown binding: $binding")}
                }
            }
        }
        override fun onItemClick(item: MovieUImodel) {
            onItemClick(item)

        }



    }



    override fun getItemViewType(position: Int): Int {
    val item = getItem(position)
    return when (item) {
        is MovieUImodel.MovieModel -> MovieClassType.MOVIE.ordinal


        is MovieUImodel.Title -> MovieClassType.TITLE.ordinal

        is MovieUImodel.Genre -> MovieClassType.GENERALS.ordinal

        else -> {
            MovieClassType.NULL.ordinal
        }
    }
}
    enum class MovieClassType {
        MOVIE,
        TITLE,
        GENERALS,
        NULL
    }
}