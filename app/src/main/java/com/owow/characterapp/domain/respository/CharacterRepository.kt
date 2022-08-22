package com.owow.characterapp.domain.respository

import com.owow.characterapp.domain.entities.CharacterResponse
import com.owow.characterapp.domain.utills.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterList(): Flow<Resource<List<CharacterResponse>>>
}