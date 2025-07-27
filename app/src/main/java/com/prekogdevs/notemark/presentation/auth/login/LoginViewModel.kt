package com.prekogdevs.notemark.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prekogdevs.notemark.domain.auth.EmailValidationUseCase
import com.prekogdevs.notemark.domain.utils.ValidationResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    val emailValidationUseCase : EmailValidationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UIState>(
        UIState(
            email = "",
            isValidEmail = false,
            password = "",
            canLogin = false,
            isLoading = false
        )
    )
    val state = _uiState.asStateFlow()

    public fun onEvent(event : Event) {
        when (event) {
            is Event.OnEmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = event.email,
                        isValidEmail = emailValidationUseCase.invoke(event.email) == ValidationResult.Valid,
                        canLogin = it.isValidEmail && it.password.isNotBlank()
                    )
                }
            }
            is Event.OnPasswordChanged -> {
                _uiState.update {
                    it.copy(
                        password = event.password,
                        canLogin = it.isValidEmail && event.password.isNotBlank()
                    )
                }
            }
            is Event.LoginClicked -> {
                login(
                    onSuccess = event.onSuccess
                )
            }
        }
    }

    private fun login(
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                if (_uiState.value.canLogin) {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                    // Simulate API call
                    // TODO: Add API call and store its result
                    delay(2000)
                    onSuccess()
                    _uiState.value = _uiState.value.copy(isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}

sealed interface Event {
    data class OnEmailChanged(val email : String) : Event
    data class OnPasswordChanged(val password : String) : Event
    data class LoginClicked(val onSuccess : () -> Unit) : Event
}

data class UIState(
    val email: String,
    val isValidEmail : Boolean,
    val password: String,
    val canLogin : Boolean,
    val isLoading : Boolean
)