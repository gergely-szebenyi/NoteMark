package com.prekogdevs.notemark.presentation.auth.di

import com.prekogdevs.notemark.domain.auth.EmailValidationUseCase
import com.prekogdevs.notemark.presentation.auth.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::LoginViewModel)
    single { EmailValidationUseCase() }
}