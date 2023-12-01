package com.example.handlingbackenderrors.presentation.model

import com.example.handlingbackenderrors.R

//changed object to normal class for memory enhancement
sealed class AuthEffect (val str: Int)
data class ShowUpdateInfoSnackbar(val msg : String):AuthEffect(R.string.user_need_to_update_info)
data class ShowLoginScreenPopup(val msg : String): AuthEffect(R.string.user_corrupted_data)
data class ShowSignupScreen(val msg : String) : AuthEffect(R.string.user_not_found)
data class ShowCustomerNotVerifiedPopup(val msg : String) : AuthEffect(R.string.user_not_verified)
data class ShowUnKnownErrorPopup(val msg: String) : AuthEffect(R.string.Unknown_error)

