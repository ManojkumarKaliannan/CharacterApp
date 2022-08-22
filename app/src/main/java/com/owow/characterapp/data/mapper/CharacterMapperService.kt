package com.owow.characterapp.data.mapper

import com.owow.characterapp.data.model.Result
import com.owow.characterapp.domain.entities.CharacterResponse

fun Result.toCharacterResponse(): CharacterResponse {
    return CharacterResponse(
        name = name,
        species = species,
        image = image,
        gender = gender,
        status = status
    )
}