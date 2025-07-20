package com.prekogdevs.notemark.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addScreen(
    baseRoute: String, // Route for screen without arguments
    content: @Composable (NavBackStackEntry) -> Unit
) {
    this.composable(
        route = baseRoute
    ) { backStackEntry ->
        content(backStackEntry)
    }
}