package com.prekogdevs.notemark.presentation.auth.login

import android.R.attr.password
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prekogdevs.notemark.R
import com.prekogdevs.notemark.presentation.NoteMarkButton
import com.prekogdevs.notemark.presentation.NoteMarkLink
import com.prekogdevs.notemark.presentation.NoteMarkTextField
import com.prekogdevs.notemark.presentation.components.NoteMarkHeader
import com.prekogdevs.notemark.presentation.landingCardModifier
import com.prekogdevs.notemark.presentation.navigation.Screen
import com.prekogdevs.notemark.ui.theme.NoteMarkTheme
import com.prekogdevs.notemark.util.DeviceConfiguration
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    navController: NavController
) {
    val uiState by viewModel.state.collectAsState()
    // TODO: API call, error handling, loading indicator
    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars
    ) { innerPadding ->
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        when (deviceConfiguration) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                MobilePortraitLoginScreen(
                    modifier = Modifier.landingCardModifier(innerPadding),
                    canLogin = uiState.canLogin,
                    email = uiState.email,
                    onEmailChanged = {
                        viewModel.onEvent(Event.OnEmailChanged(it))
                    },
                    password = uiState.password,
                    onPasswordChanged = {
                        viewModel.onEvent(Event.OnPasswordChanged(it))
                    },
                    onRegistrationClick = {
                        navController.navigate(Screen.AuthFlow.Registration.route) {
                            popUpTo(Screen.AuthFlow.Landing.route) {
                                inclusive = false
                            }
                        }
                    }
                )
            }

            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                MobileLandscapeLoginScreen(
                    modifier = Modifier.landingCardModifier(innerPadding),
                    canLogin = uiState.canLogin,
                    email = uiState.email,
                    onEmailChanged = {
                        viewModel.onEvent(Event.OnEmailChanged(it))
                    },
                    password = uiState.password,
                    onPasswordChanged = {
                        viewModel.onEvent(Event.OnPasswordChanged(it))
                    },
                    onRegistrationClick = {
                        navController.navigate(Screen.AuthFlow.Registration.route) {
                            popUpTo(Screen.AuthFlow.Landing.route) {
                                inclusive = false
                            }
                        }
                    }
                )
            }

            DeviceConfiguration.TABLET_PORTRAIT,
            DeviceConfiguration.TABLET_LANDSCAPE -> {
                TabletLoginScreen(
                    modifier = Modifier.landingCardModifier(innerPadding),
                    canLogin = uiState.canLogin,
                    email = uiState.email,
                    onEmailChanged = {
                        viewModel.onEvent(Event.OnEmailChanged(it))
                    },
                    password = uiState.password,
                    onPasswordChanged = {
                        viewModel.onEvent(Event.OnPasswordChanged(it))
                    },
                    onRegistrationClick = {
                        navController.navigate(Screen.AuthFlow.Registration.route) {
                            popUpTo(Screen.AuthFlow.Landing.route) {
                                inclusive = false
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MobilePortraitLoginScreen(
    modifier: Modifier = Modifier,
    canLogin : Boolean,
    email : String,
    onEmailChanged: (String) -> Unit,
    password : String,
    onPasswordChanged: (String) -> Unit,
    onRegistrationClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        NoteMarkHeader(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.Label_Login_Title),
            subtitle = stringResource(R.string.Label_Capture_Your_Thoughts)
        )
        LoginFormSection(
            modifier = Modifier.fillMaxWidth(),
            canLogin = canLogin,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            onRegistrationClick = onRegistrationClick
        )
    }
}

@Composable
private fun MobileLandscapeLoginScreen(
    modifier: Modifier = Modifier,
    canLogin : Boolean,
    email : String,
    onEmailChanged: (String) -> Unit,
    password : String,
    onPasswordChanged: (String) -> Unit,
    onRegistrationClick: () -> Unit
) {
    Row(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.displayCutout)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        NoteMarkHeader(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.Label_Login_Title),
            subtitle = stringResource(R.string.Label_Capture_Your_Thoughts)
        )
        LoginFormSection(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            canLogin = canLogin,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            onRegistrationClick = onRegistrationClick
        )
    }
}

@Composable
private fun TabletLoginScreen(
    modifier: Modifier = Modifier,
    canLogin : Boolean,
    email : String,
    onEmailChanged: (String) -> Unit,
    password : String,
    onPasswordChanged: (String) -> Unit,
    onRegistrationClick: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 48.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoteMarkHeader(
            modifier = Modifier.widthIn(max = 540.dp),
            alignment = Alignment.CenterHorizontally,
            title = stringResource(R.string.Label_Login_Title),
            subtitle = stringResource(R.string.Label_Capture_Your_Thoughts)
        )
        LoginFormSection(
            modifier = Modifier.widthIn(max = 540.dp),
            canLogin = canLogin,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            onRegistrationClick = onRegistrationClick
        )
    }
}

@Composable
private fun LoginFormSection(
    modifier: Modifier = Modifier,
    canLogin : Boolean,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onRegistrationClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        NoteMarkTextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            text = email,
            onValueChange = onEmailChanged,
            label = stringResource(R.string.Label_Email),
            hint = stringResource(R.string.Label_Email_Hint),
            isInputSecret = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkTextField(
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            text = password,
            onValueChange = onPasswordChanged,
            label = stringResource(R.string.Label_Password),
            hint = stringResource(R.string.Label_Password),
            isInputSecret = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        NoteMarkButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = canLogin,
            text = stringResource(R.string.Label_Login_Title),
            onClick = {
                // TODO
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkLink(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.Label_Dont_Have_Account),
            onClick = onRegistrationClick
        )
    }
}

// Phone - Portrait (e.g., Pixel 5)
@Preview(
    name = "Phone - Portrait",
    showBackground = true,
    widthDp = 412,
    heightDp = 892
)
@Composable
private fun MobilePortraitLoginScreenPreview() {
    NoteMarkTheme {
        MobilePortraitLoginScreen(
            modifier = Modifier.landingCardModifier(),
            canLogin = false,
            email = "john.doe@example.com",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            onRegistrationClick = {}
        )
    }
}

// Phone - Landscape
@Preview(
    name = "Phone - Landscape",
    showBackground = true,
    widthDp = 892,
    heightDp = 412
)
@Composable
private fun MobileLandscapeLoginScreenPreview() {
    NoteMarkTheme {
        MobileLandscapeLoginScreen(
            modifier = Modifier.landingCardModifier(),
            canLogin = false,
            email = "john.doe@example.com",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            onRegistrationClick = {}
        )
    }
}


// Tablet - Portrait (e.g., 10-inch tablet)
@Preview(
    name = "Tablet - Portrait",
    showBackground = true,
    widthDp = 800,
    heightDp = 1280
)
// Tablet - Landscape
@Preview(
    name = "Tablet - Landscape",
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
private fun TabletLoginScreenPreview() {
    NoteMarkTheme {
        TabletLoginScreen(
            modifier = Modifier.landingCardModifier(),
            canLogin = false,
            email = "john.doe@example.com",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            onRegistrationClick = {}
        )
    }
}
