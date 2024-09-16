package com.example.firstexperiment.data.remote.dto.movieDetailsDTO

import com.example.firstexperiment.domain.model.MovieDetails

data class MovieDetailsDTO(
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

fun MovieDetailsDTO.toMovieDetails(): MovieDetails {
    return MovieDetails(
    boxOfficeRevenue = boxOfficeRevenue,
    criticsScore = criticsScore,
    filmStudio = filmStudio,
    genres = genres,
    id = id,
    movieCast = movieCast,
    movieRating = movieRating,
    plotSummary = plotSummary,
    poster = poster,
    releaseDate = releaseDate,
    title = title
    )
}