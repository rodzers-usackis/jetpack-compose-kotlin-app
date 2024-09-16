package com.example.firstexperiment.presentation.viewModels.actorList

import com.example.firstexperiment.domain.model.Actor

data class ActorListState(
    val isLoading: Boolean = false,
    val actors: List<Actor> = emptyList(),
    val error: String = "")