package com.prekogdevs.notemark.presentation.auth.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prekogdevs.notemark.R
import com.prekogdevs.notemark.presentation.NoteMarkButton
import com.prekogdevs.notemark.presentation.navigation.Screen
import com.prekogdevs.notemark.ui.theme.NoteMarkTheme
import com.prekogdevs.notemark.util.DeviceConfiguration

// TODO: Reduce boilerplate
// TODO: WindowsInsets
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceConfiguration = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
    when (deviceConfiguration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            MobilePortraitLandingScreen(
                modifier = modifier,
                onRegistrationClick = {
                    navController.navigate(Screen.AuthFlow.Registration.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.AuthFlow.Login.route)
                }
            )
        }

        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            MobileLandscapeLandingScreen(
                modifier = modifier,
                onRegistrationClick = {
                    navController.navigate(Screen.AuthFlow.Registration.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.AuthFlow.Login.route)
                }
            )
        }

        DeviceConfiguration.TABLET_PORTRAIT,
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            TabletLandingScreen(
                modifier = modifier,
                onRegistrationClick = {
                    navController.navigate(Screen.AuthFlow.Registration.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.AuthFlow.Login.route)
                }
            )
        }
    }
}

@Composable
private fun MobilePortraitLandingScreen(
    modifier: Modifier = Modifier,
    onRegistrationClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceBright),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                painter = painterResource(R.drawable.landing_bg),
                contentDescription = stringResource(R.string.Label_Landing_Background_VO),
                contentScale = ContentScale.FillWidth
            )
            LandingCard(
                modifier = Modifier.align(Alignment.BottomCenter),
                onRegistrationClick = onRegistrationClick,
                onLoginClick = onLoginClick
            )
        }
    }
}

@Composable
private fun MobileLandscapeLandingScreen(
    modifier: Modifier = Modifier,
    onRegistrationClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Row(modifier = modifier.background(MaterialTheme.colorScheme.surfaceBright)) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.widthIn(min = 400.dp),
                painter = painterResource(R.drawable.landing_bg),
                contentDescription = stringResource(R.string.Label_Landing_Background_VO),
                contentScale = ContentScale.Crop
            )
            LandingCard(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .widthIn(max = 480.dp)
                    .padding(vertical = 20.dp),
                roundedCornerShape = RoundedCornerShape(
                    topStart = 20.dp,
                    bottomStart = 20.dp
                ),
                contentPadding = PaddingValues(
                    start = 60.dp,
                    top = 40.dp,
                    end = 40.dp,
                    bottom = 40.dp
                ),
                onRegistrationClick = onRegistrationClick,
                onLoginClick = onRegistrationClick
            )
        }
    }
}

@Composable
private fun TabletLandingScreen(
    modifier: Modifier = Modifier,
    onRegistrationClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                painter = painterResource(R.drawable.landing_bg),
                contentDescription = stringResource(R.string.Label_Landing_Background_VO),
                contentScale = ContentScale.FillWidth
            )
            LandingCard(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                onRegistrationClick = onRegistrationClick,
                onLoginClick = onRegistrationClick
            )
        }
    }
}

@Composable
private fun LandingCard(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    contentPadding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
    onRegistrationClick : () -> Unit,
    onLoginClick : () -> Unit
) {
    Column(
        modifier = modifier
            .clip(roundedCornerShape)
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(contentPadding),
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = stringResource(R.string.Label_Landing_Title),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.Label_Landing_Subtitle),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(40.dp))
        NoteMarkButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.Label_Get_Started),
            onClick = onRegistrationClick
        )
        Spacer(modifier = Modifier.height(12.dp))
        NoteMarkButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.Label_Log_In),
            onClick = onLoginClick,
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            textColor = MaterialTheme.colorScheme.primary
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
private fun MobilePortraitLandingScreenPreview() {
    NoteMarkTheme {
        MobilePortraitLandingScreen(
            onRegistrationClick = {},
            onLoginClick = {}
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
private fun MobileLandscapeLandingScreenPreview() {
    NoteMarkTheme {
        MobileLandscapeLandingScreen(
            onRegistrationClick = {},
            onLoginClick = {}
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
@Composable
private fun TabletPortraitLandingScreenPreview() {
    NoteMarkTheme {
        TabletLandingScreen(
            onRegistrationClick = {},
            onLoginClick = {}
        )
    }
}
