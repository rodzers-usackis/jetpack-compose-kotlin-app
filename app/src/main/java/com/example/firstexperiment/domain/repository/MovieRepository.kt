package com.example.firstexperiment.domain.repository

import com.example.firstexperiment.data.remote.dto.movieDTO.MovieDTO
import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.MovieDetailsDTO

interface MovieRepository {

    suspend fun getMovies(): List<MovieDTO>

    suspend fun getMovieByID(movieID: String): MovieDetailsDTO
}