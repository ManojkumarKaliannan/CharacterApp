package com.owow.characterapp.domain.usecase

import com.owow.characterapp.domain.entities.CharacterResponse
import com.owow.characterapp.domain.utills.Resource
import com.owow.characterapp.domain.respository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(private val repository: CharacterRepository) {
      suspend operator fun invoke ():Flow<Resource<List<CharacterResponse>>>{
       return repository.getCharacterList()
    }
}