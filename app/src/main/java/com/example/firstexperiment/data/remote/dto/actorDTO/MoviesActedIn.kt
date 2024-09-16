package com.example.firstexperiment.data.remote.dto.actorDTO

data class MoviesActedIn(
    val boxOfficeRevenue: Int,
    val criticsScore: Double,
    val filmStudio: FilmStudio,
    val genres: List<String>,
    val id: String,
    val movieRating: String,
    val plotSummary: String,
    val poster: String,
    val releaseDate: String,
    val title: String
)