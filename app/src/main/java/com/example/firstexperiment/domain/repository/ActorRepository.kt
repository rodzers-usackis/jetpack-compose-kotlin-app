package com.example.firstexperiment.domain.repository

import com.example.firstexperiment.data.remote.dto.actorDTO.ActorDTO
import com.example.firstexperiment.data.remote.dto.actorDetailsDTO.ActorDetailsDTO

interface ActorRepository {

    suspend fun getActors(): List<ActorDTO>

    suspend fun getActorByID(actorID: String): ActorDetailsDTO
}