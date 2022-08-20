package com.owow.characterapp.data.repository

import com.owow.characterapp.data.service.ICharacterApi
import com.owow.characterapp.data.model.CharacterResponse
import com.owow.characterapp.data.service.network.RetrofitClientBuilder
import com.owow.characterapp.domain.respository.CharacterRepository

 class CharacterRepositoryImpl(private val clientBuilder:RetrofitClientBuilder):CharacterRepository {
     override suspend fun getCharactersList(): CharacterResponse {
          return clientBuilder.getRetrofit().create(ICharacterApi::class.java).getAllCharacterList()
     }
}