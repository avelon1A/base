package com.example.appname.presentaion.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TransparentScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = 1f },
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransparentScreen() {
    TransparentScreen()
}
