package com.example.firstexperiment.domain.use_case.get_movies

import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.data.remote.dto.movieDTO.toMovie
import com.example.firstexperiment.domain.model.Movie
import com.example.firstexperiment.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading<List<Movie>>())
            val movies = repository.getMovies().map { it.toMovie() }
            emit(Resource.Success<List<Movie>>(movies))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "An unexpected error occurred."))

        } catch (e: IOException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))

        }
    }
}
