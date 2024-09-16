package com.example.firstexperiment.domain.model

import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.FilmStudio
import com.example.firstexperiment.data.remote.dto.movieDetailsDTO.MovieCast

data class MovieDetails(
    val boxOfficeRevenue: Int,
    val criticsScore: Double,
    val filmStudio: FilmStudio,
    val genres: List<String>,
    val id: String,
    val movieCast: List<MovieCast>,
    val movieRating: String,
    val plotSummary: String,
    val poster: String,
    val releaseDate: String,
    val title: String
)

