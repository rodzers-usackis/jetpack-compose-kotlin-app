package com.example.firstexperiment.domain.use_case.get_movie

import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.toMovieDetails
import com.example.firstexperiment.domain.model.MovieDetails
import com.example.firstexperiment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(movieID: String): Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading<MovieDetails>())
            val movie = repository.getMovieByID(movieID).toMovieDetails()
            emit(Resource.Success<MovieDetails>(movie))


        } catch (e: HttpException) {
            emit(Resource.Error<MovieDetails>(e.localizedMessage ?: "An unexpected error occurred."))

        } catch (e: IOException) {
            emit(Resource.Error<MovieDetails>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}