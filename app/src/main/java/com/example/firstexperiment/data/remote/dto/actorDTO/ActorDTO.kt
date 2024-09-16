package com.example.firstexperiment.data.remote.dto.actorDTO

import com.example.firstexperiment.domain.model.Actor

data class ActorDTO(
    val cityBorn: String,
    val countryBorn: String,
    val dateOfBirth: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val moviesActedIn: List<MoviesActedIn>,
    val portrait: String
)

fun ActorDTO.toActor(): Actor {
    return Actor(
        cityBorn = cityBorn,
        countryBorn = countryBorn,
        dateOfBirth = dateOfBirth,
        firstName = firstName,
        id = id,
        lastName = lastName,
        portrait = portrait
    )
}