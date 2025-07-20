package com.prekogdevs.notemark.domain.auth

import com.prekogdevs.notemark.domain.utils.ValidationRegex.EMAIL_REGEX
import com.prekogdevs.notemark.domain.utils.ValidationResult

class EmailValidationUseCase {

    operator fun invoke(email: String): ValidationResult = when {
        email.isEmpty() -> ValidationResult.Empty
        EMAIL_REGEX.toRegex().matches(email) -> ValidationResult.Valid
        else -> ValidationResult.Invalid()
    }
}