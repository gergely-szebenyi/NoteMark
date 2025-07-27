package com.prekogdevs.notemark.presentation

import android.transition.CircularPropagation
import androidx.collection.CircularArray
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteMarkButton(
    modifier: Modifier = Modifier,
    enabled : Boolean = true,
    isLoading : Boolean = false,
    text: String,
    onClick: () -> Unit,
    containerColor : Color = MaterialTheme.colorScheme.primary,
    textColor : Color = MaterialTheme.colorScheme.surfaceContainerLowest
) {
    Button(
        modifier = modifier.border(
            width = 1.dp,
            color = textColor,
            shape = RoundedCornerShape(10.dp),
        ),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor
        ),
        contentPadding = PaddingValues(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = textColor,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = text,
                    color = textColor,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}