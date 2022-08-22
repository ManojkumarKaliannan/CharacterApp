package com.owow.characterapp.ui.character

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.owow.characterapp.domain.usecase.GetCharacterUseCase
import com.owow.characterapp.domain.utills.Resource
import com.owow.characterapp.ui.base.BaseNavigator
import com.owow.characterapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CharacterViewModel(
    application: Application,
    private val characterUseCase: GetCharacterUseCase
) :
    BaseViewModel<BaseNavigator>(application), KoinComponent {
    private var mutableCharacterState = MutableLiveData<CharacterListState>()
    fun getCharacterListResponse(): LiveData<CharacterListState> {
        return mutableCharacterState
    }

    var progressLoading = ObservableBoolean(false)

    fun characterListRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            characterUseCase().collect {
                when (it) {
                    is Resource.Loading -> {
                        mutableCharacterState.postValue(CharacterListState(responseType = Status.LOADING))
                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            mutableCharacterState.postValue(
                                CharacterListState(
                                    responseType = Status.SUCCESSFUL,
                                    data = data
                                )
                            )
                        }
                    }
                    is Resource.Error -> {
                        mutableCharacterState.postValue(
                            CharacterListState(
                                responseType = Status.ERROR,
                                error = it.message
                            )
                        )
                    }
                }
            }
        }
    }

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }
}