package com.example.handlingbackenderrors.presentation.mapper

import com.example.handlingbackenderrors.domain.MyCustomException
import com.example.handlingbackenderrors.presentation.model.*

fun MyCustomException.handleAuthExceptions(): AuthEffect{
    return when(this){
        is MyCustomException.UserNotFoundException -> ShowSignupScreen(this.msg)
        is MyCustomException.UserNotVerifiedException -> ShowCustomerNotVerifiedPopup(this.msg)
        is MyCustomException.UserUpdateRequiredException -> ShowUpdateInfoSnackbar(this.msg)
        is MyCustomException.UserCorruptedInfoException -> ShowLoginScreenPopup(this.msg)
        is MyCustomException.UnKnownException -> ShowUnKnownErrorPopup(this.msg)
    }
}