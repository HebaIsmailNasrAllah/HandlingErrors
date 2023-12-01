package com.example.handlingbackenderrors.domain

class UseCase (private val repo: IRepo){

    fun checkIfValidLogin(): MyCustomException{
        return  repo.getBackEndResponse()
    }
}