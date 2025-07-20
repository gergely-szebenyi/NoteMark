package com.prekogdevs.notemark.presentation.auth.registration

import androidx.lifecycle.ViewModel
import com.prekogdevs.notemark.domain.auth.EmailValidationUseCase
import com.prekogdevs.notemark.domain.utils.ValidationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegistrationViewModel(
    val emailValidationUseCase : EmailValidationUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UIState>(
        UIState(
            username = "",
            email = "",
            isValidEmail = false,
            password = "",
            passwordRepeated = ""
        )
    )
    val state = _state.asStateFlow()

    public fun onEvent(event : Event) {
        when (event) {
            is Event.OnUsernameChanged -> {
                _state.update {
                    it.copy(
                        username = event.username
                    )
                }
            }
            is Event.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        isValidEmail = emailValidationUseCase.invoke(event.email) == ValidationResult.Valid,
                    )
                }
            }
            is Event.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.password
                    )
                }
            }

            is Event.OnPasswordRepeatedChanged -> {
                _state.update {
                    it.copy(
                        passwordRepeated = event.passwordRepeated
                    )
                }
            }
        }
    }

}

sealed interface Event {
    data class OnUsernameChanged(val username : String) : Event
    data class OnEmailChanged(val email : String) : Event
    data class OnPasswordChanged(val password : String) : Event
    data class OnPasswordRepeatedChanged(val passwordRepeated : String) : Event
}

data class UIState(
    val username : String,
    val email: String,
    val isValidEmail : Boolean,
    val password: String,
    val passwordRepeated: String
)