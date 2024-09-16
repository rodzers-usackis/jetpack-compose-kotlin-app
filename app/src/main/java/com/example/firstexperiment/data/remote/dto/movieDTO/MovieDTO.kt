package com.example.firstexperiment.data.remote.dto.movieDTO

import com.example.firstexperiment.domain.model.Movie

data class MovieDTO(
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

fun MovieDTO.toMovie(): Movie {
    return Movie(
        boxOfficeRevenue = boxOfficeRevenue,
        criticsScore = criticsScore,
        genres = genres,
        id = id,
        movieRating = movieRating,
        plotSummary = plotSummary,
        poster = poster,
        releaseDate = releaseDate,
        title = title,
    )
}