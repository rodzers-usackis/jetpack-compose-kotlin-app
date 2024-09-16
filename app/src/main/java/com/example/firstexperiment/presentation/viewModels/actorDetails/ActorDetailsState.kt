package com.example.firstexperiment.presentation.viewModels.actorDetails

import com.example.firstexperiment.domain.model.ActorDetails

data class ActorDetailsState(
    val isLoading: Boolean = false,
    val actor: ActorDetails? = null,
    val error: String = "")