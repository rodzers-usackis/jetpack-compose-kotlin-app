package com.example.firstexperiment.domain.model

import com.example.firstexperiment.data.remote.dto.actorDetailsDTO.MoviesActedIn

data class ActorDetails(
    val cityBorn: String,
    val countryBorn: String,
    val dateOfBirth: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val moviesActedIn: List<MoviesActedIn>,
    val portrait: String
)