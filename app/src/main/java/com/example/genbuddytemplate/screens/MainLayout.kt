package com.example.genbuddytemplate.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.genbuddytemplate.Screen
import com.example.genbuddytemplate.screens.MainContainer

@Composable
fun MainLayout(onNavigate: (Screen) -> Unit) {
    MainContainer(onNavigate = onNavigate)
}

@Preview
@Composable
fun PreviewMainLayout() {
    MainLayout(onNavigate = {})
}