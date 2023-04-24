package com.example.moviemessager.ui.fragment.moviDetail


import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviemessager.R
import com.example.moviemessager.databinding.FragmentMoviDetailBinding
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.ui.base.FragmentBaseNCMVVM
import com.example.moviemessager.ui.base.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviDetailFragment : FragmentBaseNCMVVM<MoviDetailViewModel, FragmentMoviDetailBinding>() {
    override val binding: FragmentMoviDetailBinding by viewBinding()
    override val viewModel: MoviDetailViewModel by viewModels()
    private val args: MoviDetailFragmentArgs by navArgs()
    override fun onEach() {
        onEach(viewModel.comments) {
            binding.tvComents.append("\n")
            binding.tvComents.append(it)
            binding.tvComents.append("\n")

        }
        onEach(viewModel.isFavorite) {
            if (it) {
                binding.iconFavorite.setImageResource(R.drawable.favorite_ok_24)
            } else {
                binding.iconFavorite.setImageResource(R.drawable.favorite_nok_24)
            }
        }

    }

    override fun onView() {
        viewModel.clean()
        binding.tvTitle.text = "Name :${args.movie.title}"
        binding.tvOrigenalTitle.text = "Original name: ${args.movie.original_title}"
        binding.tvyear.text = "Year relis: ${args.movie.release_date}"
        binding.tvOverview.text = args.movie.overview
        setPoster(args.movie.poster_path, binding.ivPoster)
        viewModel.searchFavoriteMovie(args.movie.id.toString())
        viewModel.initListComment(
            args.movie.id.toString(),
            { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() },
            {
                Toast.makeText(requireContext(), "auth", Toast.LENGTH_SHORT).show()
                navigateFragment(R.id.loginFragment)
            }
        )
    }

    fun setPoster(img: String, imageView: ImageView) {
        Glide.with(imageView).load("https://image.tmdb.org/t/p/w500" + img)
            .into(imageView.findViewById<ImageView>(R.id.ivPoster))
    }

    override fun onViewClick() {
        binding.btComent.setOnClickListener {
            binding.tvComents.text = ""
            viewModel.sendComment(
                args.movie.id.toString(),
                binding.edComment.editText?.text.toString()
            )
            binding.edComment.editText?.setText("")
            //Toast.makeText(requireContext(), "send ok", Toast.LENGTH_SHORT).show()

        }
        binding.btFavorite.setOnClickListener {
            val state = !viewModel.isFavorite.value

            if (state) {

                viewModel.likeMovie(args.movie)

            } else {
                viewModel.dislikeMovie(args.movie)
            }
        }


    }

    override fun onPause() {
        super.onPause()
        viewModel.clean()
    }
    companion object{
        const val TAG = "MoviDetailFragment"
}
}
