package com.example.firstexperiment.data.repository

import com.example.firstexperiment.data.remote.SelfmadeRestAPI
import com.example.firstexperiment.data.remote.dto.actorDTO.ActorDTO
import com.example.firstexperiment.data.remote.dto.actorDetailsDTO.ActorDetailsDTO
import com.example.firstexperiment.domain.repository.ActorRepository
import javax.inject.Inject

class ActorRepositoryImpl @Inject constructor(private val api: SelfmadeRestAPI): ActorRepository {

    override suspend fun getActors(): List<ActorDTO> {
        return api.getActors()
    }

    override suspend fun getActorByID(actorID: String): ActorDetailsDTO {
        return api.getActorByID(actorID)
    }
}