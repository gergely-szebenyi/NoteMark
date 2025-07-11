package com.prekogdevs.notemark.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.prekogdevs.notemark.R

@Composable
internal fun NoteMarkHeader(
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    title : String,
    subtitle : String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = alignment
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}