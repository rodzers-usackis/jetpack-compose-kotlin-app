package com.example.firstexperiment.presentation.viewModels.actorList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexperiment.common.Resource
import com.example.firstexperiment.domain.use_case.get_actors.GetActorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ActorListViewModel @Inject constructor(private val getActorsUseCase: GetActorsUseCase) : ViewModel() {
    private val _state = mutableStateOf(ActorListState())
    val state: State<ActorListState> = _state

    init {
        getActors()
    }

    private fun getActors() {
        getActorsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ActorListState(actors = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = ActorListState(error = result.message ?: "An unexpected error occurred.")
                }

                is Resource.Loading -> {
                    _state.value = ActorListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun refreshActors() {
        getActorsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ActorListState(actors = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = ActorListState(error = result.message ?: "An unexpected error occurred.")
                }

                is Resource.Loading -> {
                    _state.value = ActorListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}



