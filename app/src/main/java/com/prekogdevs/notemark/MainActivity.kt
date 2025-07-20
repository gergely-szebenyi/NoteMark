package com.prekogdevs.notemark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.prekogdevs.notemark.presentation.auth.di.authModule
import com.prekogdevs.notemark.presentation.auth.login.LoginScreen
import com.prekogdevs.notemark.presentation.auth.registration.RegistrationScreen
import com.prekogdevs.notemark.presentation.navigation.Navigation
import com.prekogdevs.notemark.ui.theme.NoteMarkTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NoteMarkTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets.statusBars
                ) { innerPadding ->
                    Navigation(
                        modifier = Modifier.fillMaxSize().padding(innerPadding).navigationBarsPadding(),
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}