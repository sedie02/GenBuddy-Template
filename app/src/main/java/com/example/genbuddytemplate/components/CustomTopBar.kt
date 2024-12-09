package com.example.genbuddytemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen

@Composable
fun CustomTopBar(onMenuClick: () -> Unit, onNavigate: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF13293D)) // Top bar color
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.White
            )
        }
        Text(
            text = "GenBuddy",
            color = Color.White,
            fontSize = 20.sp
        )
        IconButton(onClick = { onNavigate(Screen.Login) }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                tint = Color.White
            )
        }
    }
}
