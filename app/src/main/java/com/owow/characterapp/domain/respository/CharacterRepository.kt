package com.owow.characterapp.domain.respository

import com.owow.characterapp.data.model.CharacterResponse

interface CharacterRepository {
    suspend fun getCharactersList(): CharacterResponse
}