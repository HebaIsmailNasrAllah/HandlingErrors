package com.example.handlingbackenderrors.domain

interface IRepo {
    fun getBackEndResponse(): MyCustomException
}