package com.example.moviemessager.di

import com.example.moviemessager.data.apiservise.MovieApiService
import com.example.moviemessager.data.repository.MovieRepositoryImpl
import com.example.moviemessager.data.utils.HeaderInterceptor
import com.example.moviemessager.di.NetworkModule.provideRetrofitMovie
import com.example.moviemessager.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


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
    fun provideApiModule():MovieApiService=provideRetrofitMovie().create(MovieApiService::class.java)
}
@Module
@InstallIn(ViewModelComponent::class)
class  MovieRepositoryModule {
    @Provides
    fun provideRepositoryModule(api:MovieApiService): MovieRepository = MovieRepositoryImpl(api)
}
