package com.owow.characterapp.data.repository

import com.owow.characterapp.data.mapper.toCharacterResponse
import com.owow.characterapp.data.service.ICharacterApi
import com.owow.characterapp.data.service.network.NoConnectivityException
import com.owow.characterapp.data.service.network.RetrofitClientBuilder
import com.owow.characterapp.domain.entities.CharacterResponse
import com.owow.characterapp.domain.respository.CharacterRepository
import com.owow.characterapp.domain.utills.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class CharacterRepositoryImpl(private val clientBuilder: RetrofitClientBuilder) :
    CharacterRepository {
    override suspend fun getCharacterList(): Flow<Resource<List<CharacterResponse>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val api = clientBuilder.getRetrofit().create(ICharacterApi::class.java)
                    .getAllCharacterList()
                emit(Resource.Success(api.results.map { it.toCharacterResponse() }))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "An unexpected error occured"))
            } catch (e: NoConnectivityException) {
                emit(Resource.Error(e.message))
            }
        }
    }
}