package com.owow.characterapp.ui.character

import com.owow.characterapp.domain.entities.CharacterResponse

data class CharacterListState(
    var responseType: Status,
    var data: List<CharacterResponse> = emptyList(),
    var error: String? = null
)

enum class Status { SUCCESSFUL, ERROR, LOADING }