package com.owow.characterapp.ui.character
import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.owow.characterapp.domain.model.Resource
import com.owow.characterapp.domain.usecase.GetCharacterUseCase
import com.owow.characterapp.ui.base.BaseNavigator
import com.owow.characterapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class CharacterViewModel(application: Application,private val useCase: GetCharacterUseCase):BaseViewModel<BaseNavigator>(application),KoinComponent {
    private var mutableCharacterState=MutableLiveData<CharacterListState>()
    fun getCharacterListResponse(): LiveData<CharacterListState> {
        return mutableCharacterState
    }
    var progressLoading=ObservableBoolean(false)

    fun characterListRequest(){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.invoke().collect {
                when(it){
                    is Resource.Loading->{
                        mutableCharacterState.postValue(CharacterListState(Status.LOADING))
                    }
                    is Resource.Success ->{
                        mutableCharacterState.postValue(CharacterListState(Status.SUCCESSFUL,data=it.data))
                    }
                    is Resource.Error ->{
                        mutableCharacterState.postValue(CharacterListState(Status.ERROR, error = it.message))
                    }
                    else -> {
                        progressLoading.set(false)
                    }
                }
            }
        }
    }

    fun onClickAction(view: View?) {
        getNavigator().onClickView(view)
    }
}