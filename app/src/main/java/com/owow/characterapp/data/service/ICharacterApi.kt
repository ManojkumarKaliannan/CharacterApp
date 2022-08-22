package com.owow.characterapp.data.service

import com.owow.characterapp.data.model.CharacterAPIResponse
import retrofit2.http.GET

interface ICharacterApi {
    @GET("api/character")
    suspend fun getAllCharacterList(): CharacterAPIResponse
}