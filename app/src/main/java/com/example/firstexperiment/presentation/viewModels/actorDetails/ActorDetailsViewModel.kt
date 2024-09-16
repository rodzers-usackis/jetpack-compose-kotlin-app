package com.example.firstexperiment.presentation.viewModels.actorDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexperiment.common.Constants
import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.domain.use_case.get_actor.GetActorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ActorDetailsViewModel @Inject constructor(
    private val getActorUseCase: GetActorUseCase,
    savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = mutableStateOf(ActorDetailsState())
    val state: State<ActorDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ACTOR_ID)?.let { actorID ->
            getActor(actorID)
        }
    }

    private fun getActor(actorID: String) {
        getActorUseCase(actorID).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ActorDetailsState(actor = result.data)
                }

                is Resource.Error -> {
                    _state.value = ActorDetailsState(error = result.message ?: "An unexpected error occurred.")
                }

                is Resource.Loading -> {
                    _state.value = ActorDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}