package com.example.handlingbackenderrors.domain

import com.example.handlingbackenderrors.presentation.mapper.handleAuthExceptions


sealed class MyCustomException(val errorMsg: String) : Exception() {
    class UserNotFoundException(val msg: String) : MyCustomException(msg)
    class UserNotVerifiedException(val msg: String) : MyCustomException(msg)
    class UserUpdateRequiredException(val msg: String) : MyCustomException(msg)
    class UserCorruptedInfoException(val msg: String) : MyCustomException(msg)
    class UnKnownException(val msg: String):MyCustomException(msg)
}

enum class ErrorCode {
    USER_NOT_FOUND,
    NOT_VERIFIED_USER,
    CORRUPTED_DATA,
    NEED_TO_UPDATE_INFO
}

fun ErrorCode.toCustomException(errorMsg: String): MyCustomException {
    return when (errorMsg) {
        ErrorCode.USER_NOT_FOUND.name -> MyCustomException.UserNotFoundException(errorMsg)
        ErrorCode.NOT_VERIFIED_USER.name -> MyCustomException.UserNotVerifiedException(errorMsg)
        ErrorCode.CORRUPTED_DATA.name -> MyCustomException.UserCorruptedInfoException(errorMsg)
        ErrorCode.NEED_TO_UPDATE_INFO.name -> MyCustomException.UserUpdateRequiredException(errorMsg)
        else -> MyCustomException.UnKnownException(errorMsg)
    }
}