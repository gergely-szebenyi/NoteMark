package com.prekogdevs.notemark.presentation.navigation

enum class Route {
    AUTH_ENTRY,
    LANDING,
    LOGIN,
    REGISTRATION
}

sealed class Screen(val route: String) {
    data object AuthFlow : Screen(Route.AUTH_ENTRY.name) {
        data object Landing : Screen(Route.LANDING.name)
        data object Login : Screen(Route.LOGIN.name)
        data object Registration : Screen(Route.REGISTRATION.name)
    }
}