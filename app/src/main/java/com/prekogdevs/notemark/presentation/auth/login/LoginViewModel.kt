package com.prekogdevs.notemark.presentation.auth.login

import androidx.lifecycle.ViewModel
import com.prekogdevs.notemark.domain.auth.EmailValidationUseCase
import com.prekogdevs.notemark.domain.utils.ValidationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    val emailValidationUseCase : EmailValidationUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UIState>(
        UIState(
            email = "",
            isValidEmail = false,
            password = "",
            canLogin = false
        )
    )
    val state = _state.asStateFlow()

    public fun onEvent(event : Event) {
        when (event) {
            is Event.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        isValidEmail = emailValidationUseCase.invoke(event.email) == ValidationResult.Valid,
                        canLogin = it.isValidEmail && it.password.isNotBlank()
                    )
                }
            }
            is Event.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password,
                        canLogin = it.isValidEmail && event.password.isNotBlank()
                    )
                }
            }
        }
    }

}

sealed interface Event {
    data class OnEmailChanged(val email : String) : Event
    data class OnPasswordChanged(val password : String) : Event
}

data class UIState(
    val email: String,
    val isValidEmail : Boolean,
    val password: String,
    val canLogin : Boolean
)