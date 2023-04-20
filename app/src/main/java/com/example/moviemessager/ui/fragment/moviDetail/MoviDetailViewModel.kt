package com.example.moviemessager.ui.fragment.moviDetail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviemessager.domain.interactor.*
import com.example.moviemessager.domain.model.MovieUImodel
import com.example.moviemessager.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviDetailViewModel@Inject constructor(private val initListCommentUseCase: InitListCommentUseCase,
                                             private val sendCommentUseCase: SendCommentUseCase,
                                             private val searchFavoriteMovieUseCase: SearchFavoriteMovieUseCase,
                                             private val likeMovieUseCase: LikeMovieUseCase,
                                             private val dislikeMovieUseCase: DislikeMovieUseCase
                                             ) : BaseViewModel() {
    private val _comments: MutableStateFlow<String> = MutableStateFlow("")

    val comments = _comments.asStateFlow()

    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val isFavorite = _isFavorite.asStateFlow()

    fun initListComment(movieId: String,
                        error: (error: String) -> Unit,
                        noUser: () -> Unit){
        viewModelScope.launch{
            _comments.emit("")
            initListCommentUseCase(movieId, {
            _comments.value=it
            }, error, noUser)
        }
    }
    fun sendComment(movieId: String,message:String){
        viewModelScope.launch{
            _comments.emit("")
            sendCommentUseCase(message, movieId )
        }
    }

    fun likeMovie(movie: MovieUImodel.MovieModel){
        viewModelScope.launch{
            likeMovieUseCase(movie)
            _isFavorite.emit(true)
        }
    }
    fun dislikeMovie(movie: MovieUImodel.MovieModel){
        viewModelScope.launch{
            dislikeMovieUseCase(movie)
            _isFavorite.emit(false)
        }
    }



    fun searchFavoriteMovie(value:String){
        viewModelScope.launch{
          searchFavoriteMovieUseCase(value).collectLatest {
              _isFavorite.emit(it!=null)
          }
        }
    }
}