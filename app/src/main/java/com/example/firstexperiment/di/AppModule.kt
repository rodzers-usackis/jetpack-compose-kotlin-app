package com.example.firstexperiment.di

import com.example.firstexperiment.common.Constants
import com.example.firstexperiment.data.remote.SelfmadeRestAPI
import com.example.firstexperiment.data.repository.ActorRepositoryImpl
import com.example.firstexperiment.data.repository.MovieRepositoryImpl
import com.example.firstexperiment.domain.repository.ActorRepository
import com.example.firstexperiment.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Dependency Injection
@Module
@InstallIn(SingletonComponent::class) // All dependencies in the module live as long as the application does.
object AppModule {

    @Provides
    @Singleton
    fun provideSelfmadeRestAPI(): SelfmadeRestAPI {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SelfmadeRestAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: SelfmadeRestAPI): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideActorRepository(api: SelfmadeRestAPI): ActorRepository {
        return ActorRepositoryImpl(api)
    }
}