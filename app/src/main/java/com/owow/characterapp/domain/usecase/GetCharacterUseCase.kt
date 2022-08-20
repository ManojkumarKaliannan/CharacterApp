package com.owow.characterapp.domain.usecase

import com.owow.characterapp.data.model.CharacterResponse
import com.owow.characterapp.data.service.network.NoConnectivityException
import com.owow.characterapp.domain.model.Resource
import com.owow.characterapp.domain.respository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class GetCharacterUseCase(private val repository: CharacterRepository) {
    operator fun invoke(): Flow<Resource<CharacterResponse>?> =
        flow {
            try {
                emit(Resource.Loading())
                val users = repository.getCharactersList()
                emit(Resource.Success(users))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "An unexpected error occured"))
            } catch (e: NoConnectivityException) {
                emit(Resource.Error(e.message))
            }
        }
}