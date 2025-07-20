package com.prekogdevs.notemark.presentation.auth.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    // TODO: Move logic to VM
    // TODO: Reduce copy paste
    // TODO: Make screen scrollable?
    // TODO: Top part of the screen does not match design
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeated by remember { mutableStateOf("") }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            MobilePortraitRegistrationScreen(
                modifier = modifier.landingCardModifier(),
                username = username,
                onUsernameChanged = { username = it },
                email = email,
                onEmailChanged = { email = it },
                password = password,
                onPasswordChanged = { password = it },
                passwordRepeated = passwordRepeated,
                onRepeatedPasswordChanged = { passwordRepeated = it },
                onAlreadyHaveAnAccountClick = {
                    navController.navigate(Screen.AuthFlow.Login.route)
                }
            )
        }

        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            MobileLandscapeRegistrationScreen(
                modifier = modifier.landingCardModifier(),
                username = username,
                onUsernameChanged = { username = it },
                email = email,
                onEmailChanged = { email = it },
                password = password,
                onPasswordChanged = { password = it },
                passwordRepeated = passwordRepeated,
                onRepeatedPasswordChanged = { passwordRepeated = it },
                onAlreadyHaveAnAccountClick = {
                    navController.navigate(Screen.AuthFlow.Login.route)
                }
            )
        }

        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            TabletRegistrationScreen(
                modifier = modifier.landingCardModifier(),
                username = username,
                onUsernameChanged = { username = it },
                email = email,
                onEmailChanged = { email = it },
                password = password,
                onPasswordChanged = { password = it },
                passwordRepeated = passwordRepeated,
                onRepeatedPasswordChanged = { passwordRepeated = it },
                onAlreadyHaveAnAccountClick = {
                    navController.navigate(Screen.AuthFlow.Login.route)
                }
            )
        }
    }
}

@Composable
private fun MobilePortraitRegistrationScreen(
    modifier: Modifier = Modifier,
    username: String,
    onUsernameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordRepeated: String,
    onRepeatedPasswordChanged: (String) -> Unit,
    onAlreadyHaveAnAccountClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        NoteMarkHeader(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.Label_Create_Account),
            subtitle = stringResource(R.string.Label_Capture_Your_Thoughts)
        )
        RegistrationFormSection(
            modifier = Modifier.fillMaxWidth(),
            username = username,
            onUsernameChanged = onUsernameChanged,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            passwordRepeated = passwordRepeated,
            onRepeatedPasswordChanged = onRepeatedPasswordChanged,
            onAlreadyHaveAnAccountClick = onAlreadyHaveAnAccountClick
        )
    }
}

@Composable
private fun MobileLandscapeRegistrationScreen(
    modifier: Modifier = Modifier,
    username: String,
    onUsernameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordRepeated: String,
    onRepeatedPasswordChanged: (String) -> Unit,
    onAlreadyHaveAnAccountClick: () -> Unit
) {
    Row(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.displayCutout)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        NoteMarkHeader(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.Label_Create_Account),
            subtitle = stringResource(R.string.Label_Capture_Your_Thoughts)
        )
        RegistrationFormSection(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            username = username,
            onUsernameChanged = onUsernameChanged,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            passwordRepeated = passwordRepeated,
            onRepeatedPasswordChanged = onRepeatedPasswordChanged,
            onAlreadyHaveAnAccountClick = onAlreadyHaveAnAccountClick
        )
    }
}

@Composable
private fun TabletRegistrationScreen(
    modifier: Modifier = Modifier,
    username: String,
    onUsernameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordRepeated: String,
    onRepeatedPasswordChanged: (String) -> Unit,
    onAlreadyHaveAnAccountClick: () -> Unit
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
            title = stringResource(R.string.Label_Create_Account),
            subtitle = stringResource(R.string.Label_Capture_Your_Thoughts)
        )
        RegistrationFormSection(
            modifier = Modifier.widthIn(max = 540.dp),
            username = username,
            onUsernameChanged = onUsernameChanged,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            passwordRepeated = passwordRepeated,
            onRepeatedPasswordChanged = onRepeatedPasswordChanged,
            onAlreadyHaveAnAccountClick = onAlreadyHaveAnAccountClick
        )
    }
}

@Composable
private fun RegistrationFormSection(
    modifier: Modifier = Modifier,
    username: String,
    onUsernameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordRepeated: String,
    onRepeatedPasswordChanged: (String) -> Unit,
    onAlreadyHaveAnAccountClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        NoteMarkTextField(
            modifier = Modifier.fillMaxWidth(),
            text = username,
            onValueChange = onUsernameChanged,
            label = stringResource(R.string.Label_Registration_Username),
            hint = stringResource(R.string.Label_Registration_Username_Hint),
            isInputSecret = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkTextField(
            modifier = Modifier.fillMaxWidth(),
            text = email,
            onValueChange = onEmailChanged,
            label = stringResource(R.string.Label_Email),
            hint = stringResource(R.string.Label_Email_Hint),
            isInputSecret = false
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkTextField(
            modifier = Modifier.fillMaxWidth(),
            text = password,
            onValueChange = onPasswordChanged,
            label = stringResource(R.string.Label_Password),
            hint = stringResource(R.string.Label_Password),
            isInputSecret = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkTextField(
            modifier = Modifier.fillMaxWidth(),
            text = passwordRepeated,
            onValueChange = onRepeatedPasswordChanged,
            label = stringResource(R.string.Label_Repeat_Password),
            hint = stringResource(R.string.Label_Password),
            isInputSecret = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        NoteMarkButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.Label_Create_Account),
            onClick = {
                // TODO
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        NoteMarkLink(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.Label_Already_Have_Account),
            onClick = onAlreadyHaveAnAccountClick
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
private fun MobilePortraitRegistrationScreenPreview() {
    NoteMarkTheme {
        MobilePortraitRegistrationScreen(
            modifier = Modifier.landingCardModifier(),
            username = "John.doe",
            onUsernameChanged = {},
            email = "john.doe@example.com",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            passwordRepeated = "",
            onRepeatedPasswordChanged = {},
            onAlreadyHaveAnAccountClick = {}
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
private fun MobileLandscapeRegistrationScreenPreview() {
    NoteMarkTheme {
        MobileLandscapeRegistrationScreen(
            modifier = Modifier.landingCardModifier(),
            username = "John.doe",
            onUsernameChanged = {},
            email = "john.doe@example.com",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            passwordRepeated = "",
            onRepeatedPasswordChanged = {},
            onAlreadyHaveAnAccountClick = {}
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
private fun TabletRegistrationScreenPreview() {
    NoteMarkTheme {
        TabletRegistrationScreen(
            modifier = Modifier.landingCardModifier(),
            username = "John.doe",
            onUsernameChanged = {},
            email = "john.doe@example.com",
            onEmailChanged = {},
            password = "",
            onPasswordChanged = {},
            passwordRepeated = "",
            onRepeatedPasswordChanged = {},
            onAlreadyHaveAnAccountClick = {}
        )
    }
}