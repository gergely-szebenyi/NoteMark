package com.prekogdevs.notemark.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
// TODO: Implement correct backtack handling
fun Navigation(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.AuthFlow.Landing.route // TODO: Decide based on Login status
    ) {
        authGraph(navController)
    }
}