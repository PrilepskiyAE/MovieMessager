package com.example.moviemessager.ui.fragment.moviDetail


import android.util.Log
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentMoviDetailBinding
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding



class MoviDetailFragment : FragmentBaseNCMVVM<MoviDetailViewModel, FragmentMoviDetailBinding>()  {
    override val binding:FragmentMoviDetailBinding by viewBinding()
    override val viewModel: MoviDetailViewModel by viewModels()
    val args: MoviDetailFragmentArgs by navArgs()
    override fun onView() {

        Log.d("TAG99", "onView: ${args.movie}")
        binding.tvTitle.text="Name :${args.movie.title}"
        binding.tvOrigenalTitle.text="Original name: ${args.movie.original_title}"
        binding.tvyear.text="Year relis: ${args.movie.release_date}"
        binding.tvOverview.text= args.movie.overview
        setPoster(args.movie.poster_path,binding.ivPoster)
        viewModel.initListComment(args.movie.id.toString(),{},{},{})
    }
    fun  setPoster( img:String, imageView: ImageView)
    {
        Glide.with(imageView).load("https://image.tmdb.org/t/p/w500"+img)
            .into(imageView.findViewById<ImageView>(R.id.ivPoster))
    }

    override fun onViewClick() {
        binding.btComent.setOnClickListener {
            viewModel.sendComment(args.movie.id.toString(),binding.edComment.editText?.text.toString())
        }

    }
}
