package com.example.moviemessager.di

import android.content.Context
import androidx.room.Room
import com.example.moviemessager.data.apiservise.MovieApiService
import com.example.moviemessager.data.dbservise.FavoriteDataBase
import com.example.moviemessager.data.repository.*
import com.example.moviemessager.data.utils.HeaderInterceptor
import com.example.moviemessager.domain.interactor.*
import com.example.moviemessager.domain.repository.*
import com.example.moviemessager.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class DBModule {
    @Provides
    fun provideDB(@ApplicationContext context: Context): FavoriteDataBase = Room.databaseBuilder(
        context,
        FavoriteDataBase::class.java, "FavoriteDB"
    ).build()
}


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitMovie() :Retrofit=
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
                )
            }
            .build()
        }

@Module
@InstallIn(ViewModelComponent::class)
class MovieapiModule(){
    @Provides
    fun provideApiModule(retrofit: Retrofit):MovieApiService=retrofit.create(MovieApiService::class.java)
}
@Module
@InstallIn(ViewModelComponent::class)
class  MovieRepositoryModule {
    @Provides
    fun provideRepositoryModule(api:MovieApiService): MovieRepository = MovieRepositoryImpl(api)

    @Provides
    fun provideAuthenticationRepositoryModule(): AuthenticationRepository = AuthenticationRepositoryImpl()

    @Provides
    fun provideCommentsRepositoryModule(): CommentsRepository = CommentsRepositoryImpl()

    @Provides
    fun provideMessageRepositoryModule(): MessageRepository = MessageRepositoryImpl()

    @Provides
    fun provideFavoriteRepositoryModule(db:FavoriteDataBase): FavoriteRepository = FavoriteRepositoryImpl(db)


}
@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetListMovieUseCaseModule(movieRepository: MovieRepository): GetListMovieUseCase=GetListMovieUseCaseImpl(movieRepository)

    @Provides
    fun provideCheckIsLoginUseCaseModule(authenticationRepository: AuthenticationRepository): CheckIsLoginUseCase =
        CheckIsLoginUseCaseImpl(authenticationRepository)


    @Provides
    fun provideFirebaseAuthWithGoogleUseCaseModule(authenticationRepository: AuthenticationRepository): FirebaseAuthWithGoogleUseCase =
        FirebaseAuthWithGoogleUseCaseImpl(authenticationRepository)

    @Provides
    fun provideLoginBasicAuthUseCaseModule(authenticationRepository: AuthenticationRepository): LoginBasicAuthUseCase =
        LoginBasicAuthUseCaseImpl(authenticationRepository)

    @Provides
    fun provideLogoutUseCaseModule(authenticationRepository: AuthenticationRepository): LogoutUseCase =
        LogoutUseCaseImpl(authenticationRepository)

    @Provides
    fun provideRegisterBasicAuthUseCaseModule(authenticationRepository: AuthenticationRepository): RegisterBasicAuthUseCase =
        RegisterBasicAuthUseCaseImpl(authenticationRepository)

    @Provides
    fun provideSignInWithGoogleUseCaseModule(authenticationRepository: AuthenticationRepository): SignInWithGoogleUseCase =
        SignInWithGoogleUseCaseImpl(authenticationRepository)

    @Provides
    fun provideInitListUsersFirebaseUseCaseModule(authenticationRepository: AuthenticationRepository): InitListUsersFirebaseUseCase =
        InitListUsersFirebaseUseCaseImpl(authenticationRepository)

    @Provides
    fun provideGetEmailUseCaseModule(authenticationRepository: AuthenticationRepository): GetEmailUseCase =
        GetEmailUseCaseImpl(authenticationRepository)

    @Provides
    fun provideGetUserUseCaseModule(authenticationRepository: AuthenticationRepository): GetUserUseCase =
        GetUserUseCaseImpl(authenticationRepository)


    @Provides
    fun provideInitListCommentUseCaseModule(commentsRepository:CommentsRepository): InitListCommentUseCase =
        InitListCommentUseCaseImpl(commentsRepository)



    @Provides
    fun provideSendCommentUseCaseModule(commentsRepository:CommentsRepository): SendCommentUseCase =
        SendCommentUseCaseImpl(commentsRepository)


    @Provides
    fun provideDislikeMovieUseCaseModule(favoriteRepository:FavoriteRepository): DislikeMovieUseCase =
        DislikeMovieUseCaseImpl(favoriteRepository)

    @Provides
    fun provideLikeMovieUseCaseModule(favoriteRepository:FavoriteRepository): LikeMovieUseCase =
        LikeMovieUseCaseImpl(favoriteRepository)

    @Provides
    fun provideSearchFavoriteMovieUseCaseModule(favoriteRepository:FavoriteRepository): SearchFavoriteMovieUseCase =
        SearchFavoriteMovieUseCaseImpl(favoriteRepository)

    @Provides
    fun provideGetListFavoriteMovieUseCaseModule(favoriteRepository:FavoriteRepository): GetListFavoriteMovieUseCase =
        GetListFavoriteMovieUseCaseImpl(favoriteRepository)


    @Provides
    fun provideInitListMessageUseCaseModule(messageRepository:MessageRepository): InitListMessageUseCase =
        InitListMessageUseCaseImpl(messageRepository)

    @Provides
    fun provideSendMessageUseCaseModule(messageRepository:MessageRepository): SendMessageUseCase =
        SendMessageUseCaseImpl(messageRepository)


}



