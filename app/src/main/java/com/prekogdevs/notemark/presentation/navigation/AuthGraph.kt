package com.prekogdevs.notemark.presentation.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.prekogdevs.notemark.presentation.auth.landing.LandingScreen
import com.prekogdevs.notemark.presentation.auth.login.LoginScreen
import com.prekogdevs.notemark.presentation.auth.login.LoginViewModel
import com.prekogdevs.notemark.presentation.auth.registration.RegistrationScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.authGraph(
    navController: NavHostController
) {
    addScreen(baseRoute = Screen.AuthFlow.Landing.route) {
        LandingScreen(
            navController = navController
        )
    }
    addScreen(baseRoute = Screen.AuthFlow.Login.route) {
        LoginScreen(
            viewModel = koinViewModel(),
            navController = navController
        )
    }
    addScreen(baseRoute = Screen.AuthFlow.Registration.route) {
        RegistrationScreen(
            navController = navController,
            viewModel = koinViewModel()
        )
    }
}
