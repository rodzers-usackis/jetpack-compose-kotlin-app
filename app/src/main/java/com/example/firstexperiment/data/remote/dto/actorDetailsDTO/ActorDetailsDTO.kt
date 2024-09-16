package com.example.firstexperiment.data.remote.dto.actorDetailsDTO

import com.example.firstexperiment.domain.model.ActorDetails

data class ActorDetailsDTO(
    val cityBorn: String,
    val countryBorn: String,
    val dateOfBirth: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val moviesActedIn: List<MoviesActedIn>,
    val portrait: String
)

fun ActorDetailsDTO.toActorDetails(): ActorDetails {
    return ActorDetails(
    cityBorn = cityBorn,
    countryBorn = countryBorn,
    dateOfBirth = dateOfBirth,
    firstName = firstName,
    id = id,
    lastName = lastName,
    moviesActedIn = moviesActedIn,
    portrait =  portrait
    )
}