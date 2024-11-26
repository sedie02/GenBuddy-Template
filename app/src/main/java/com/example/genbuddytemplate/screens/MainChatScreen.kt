package com.example.genbuddytemplate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.genbuddytemplate.Screen

@Composable
fun MainChatScreen(onNavigate: (Screen) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Main Chat Screen")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onNavigate(Screen.Onboarding) }) {
                Text(text = "Go to Onboarding Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onNavigate(Screen.Login) }) {
                Text(text = "Go to Login Screen")
            }
        }
    }
}
