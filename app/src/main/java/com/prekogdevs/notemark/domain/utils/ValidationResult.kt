package com.prekogdevs.notemark.domain.utils

sealed interface ValidationResult {
    data object Valid : ValidationResult
    data class Invalid(val message: String? = null) : ValidationResult
    data object Empty : ValidationResult
}