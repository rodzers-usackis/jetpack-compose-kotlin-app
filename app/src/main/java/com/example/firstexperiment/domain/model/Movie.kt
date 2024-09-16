package com.example.firstexperiment.domain.model

import com.example.firstexperiment.data.remote.dto.movieDTO.FilmStudio
import com.example.firstexperiment.data.remote.dto.movieDTO.MovieCast

data class Movie(
    val boxOfficeRevenue: Int,
    val criticsScore: Double,
    val genres: List<String>,
    val id: String,
    val movieRating: String,
    val plotSummary: String,
    val poster: String,
    val releaseDate: String,
    val title: String
)
