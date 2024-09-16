package com.example.firstexperiment.presentation.viewModels.movieDetails

import com.example.firstexperiment.domain.model.MovieDetails

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: MovieDetails? = null,
    val error: String = "")
