package com.example.firstexperiment.domain.use_case.get_actor

import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.data.remote.dto.actorDetailsDTO.toActorDetails
import com.example.firstexperiment.domain.model.ActorDetails
import com.example.firstexperiment.domain.repository.ActorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetActorUseCase @Inject constructor(private val repository: ActorRepository) {

    operator fun invoke(actorID: String): Flow<Resource<ActorDetails>> = flow {
        try {
            emit(Resource.Loading<ActorDetails>())
            val actor = repository.getActorByID(actorID).toActorDetails()
            emit(Resource.Success<ActorDetails>(actor))

        } catch (e: HttpException) {
            emit(Resource.Error<ActorDetails>(e.localizedMessage ?: "An unexpected error occurred."))

        } catch (e: IOException) {
            emit(Resource.Error<ActorDetails>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }
    }
}