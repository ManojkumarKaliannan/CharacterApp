package com.owow.characterapp.ui.di

import com.owow.characterapp.data.repository.CharacterRepositoryImpl
import com.owow.characterapp.data.service.network.ConnectionInterceptor
import com.owow.characterapp.data.service.network.RetrofitClientBuilder
import com.owow.characterapp.domain.respository.CharacterRepository
import com.owow.characterapp.domain.usecase.GetCharacterUseCase
import com.owow.characterapp.ui.character.CharacterViewModel
import com.owow.characterapp.utills.ConnectionService
import com.owow.characterapp.utills.SharedPreference
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

class AppModule {
    val viewModel = module {
        single { CharacterViewModel(androidApplication(),get()) }
    }
    val networkModule = module {
        single { ConnectionInterceptor(get()) }
        single { ConnectionService(androidApplication()) }
        single { RetrofitClientBuilder(get()) }
    }
    val repository = module {
        single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    }
    val useCaseModule = module {
        single { GetCharacterUseCase(get()) }
    }
    val preference = module {
        single { SharedPreference(get()) }
    }
}