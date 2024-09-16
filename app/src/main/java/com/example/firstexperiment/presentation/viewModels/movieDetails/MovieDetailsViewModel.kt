package com.example.firstexperiment.presentation.viewModels.movieDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexperiment.common.Constants
import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.domain.use_case.get_movie.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let { movieID ->
            getMovie(movieID)
        }
    }

    private fun getMovie(movieID: String) {
        getMovieUseCase(movieID).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = MovieDetailsState(movie = result.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailsState(error = result.message ?: "An unexpected error occurred.")
                }

                is Resource.Loading -> {
                    _state.value = MovieDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}