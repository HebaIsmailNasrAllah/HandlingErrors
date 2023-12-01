package com.example.handlingbackenderrors.presentation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.handlingbackenderrors.domain.MyCustomException
import com.example.handlingbackenderrors.domain.UseCase
import com.example.handlingbackenderrors.presentation.mapper.handleAuthExceptions
import com.example.handlingbackenderrors.presentation.model.AuthState
import com.example.handlingbackenderrors.presentation.model.AuthEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyViewModel(private val useCase: UseCase): ViewModel() {
    private val _state : MutableStateFlow<AuthState> = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    private val _errorState: MutableSharedFlow<AuthEffect> = MutableSharedFlow()
    val errorState: SharedFlow<AuthEffect> = _errorState

    init {

    }
    fun getData(){
        viewModelScope.launch {
            try{
                useCase.checkIfValidLogin()
            }catch(ex: MyCustomException){
                _errorState.emit(ex.handleAuthExceptions())
            }
        }
    }


    class MyFactory(private val useCase: UseCase): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if(modelClass.isAssignableFrom(MyViewModel::class.java)){
                MyViewModel(useCase) as T
            }else{
                throw java.lang.IllegalArgumentException("Can't Create ViewModel")
            }
        }
    }


}