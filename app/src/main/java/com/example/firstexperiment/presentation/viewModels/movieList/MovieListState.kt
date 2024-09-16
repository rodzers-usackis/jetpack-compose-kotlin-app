package com.example.firstexperiment.presentation.viewModels.movieList

import com.example.firstexperiment.domain.model.Movie

data class MovieListState(
    var isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "")
