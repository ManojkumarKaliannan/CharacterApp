package com.owow.characterapp.ui.character

import com.owow.characterapp.data.model.CharacterResponse

data class CharacterListState(var responseType: Status, var data: CharacterResponse? = null, var error: String? = null)

enum class Status { SUCCESSFUL, ERROR, LOADING}