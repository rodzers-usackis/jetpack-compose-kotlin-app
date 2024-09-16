package com.example.firstexperiment.domain.use_case.get_actors

import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.data.remote.dto.actorDTO.toActor
import com.example.firstexperiment.domain.model.Actor
import com.example.firstexperiment.domain.repository.ActorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetActorsUseCase @Inject constructor(private val repository: ActorRepository) {

    operator fun invoke(): Flow<Resource<List<Actor>>> = flow {
        try {
            emit(Resource.Loading<List<Actor>>())
            val actors = repository.getActors().map { it.toActor() }
            emit(Resource.Success<List<Actor>>(actors))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Actor>>(e.localizedMessage ?: "An unexpected error occurred."))

        } catch (e: IOException) {
            emit(Resource.Error<List<Actor>>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))

        }
    }
}