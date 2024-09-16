package com.example.firstexperiment.data.remote

import com.example.firstexperiment.data.remote.dto.actorDTO.ActorDTO
import com.example.firstexperiment.data.remote.dto.actorDetailsDTO.ActorDetailsDTO
import com.example.firstexperiment.data.remote.dto.movieDTO.MovieDTO
import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.MovieDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface SelfmadeRestAPI {

    @GET("/api/movies")
    suspend fun getMovies(): List<MovieDTO>

    @GET("/api/movies/{movieID}")
    suspend fun getMovieByID(@Path("movieID") movieID: String): MovieDetailsDTO

    @GET("/api/actors")
    suspend fun getActors(): List<ActorDTO>

    @GET("/api/actors/{actorID}")
    suspend fun getActorByID(@Path("actorID") actorID: String): ActorDetailsDTO
}
