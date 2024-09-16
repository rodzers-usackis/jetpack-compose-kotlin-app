package com.example.firstexperiment.data.repository

import com.example.firstexperiment.data.remote.SelfmadeRestAPI
import com.example.firstexperiment.data.remote.dto.movieDTO.MovieDTO
import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.MovieDetailsDTO
import com.example.firstexperiment.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: SelfmadeRestAPI): MovieRepository {

    override suspend fun getMovies(): List<MovieDTO> {
        return api.getMovies()
    }

    override suspend fun getMovieByID(movieID: String): MovieDetailsDTO {
        return api.getMovieByID(movieID)
    }
}

