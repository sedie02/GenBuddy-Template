package com.example.genbuddytemplate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen

@Composable
fun DrawerContent(onNavigate: (Screen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75f) // Drawer width: 75% of the screen
            .background(Color(0xFF006495))
            .padding(16.dp)
    ) {
        // User Information Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp) // Circle size
                    .clip(CircleShape)
                    .background(Color.White) // Placeholder for profile picture
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Sedie-Man", // Replace with dynamic username in the future
                    color = Color.White,
                    fontSize = 18.sp
                )
                Text(
                    text = "user@example.com", // Replace with dynamic email in the future
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        // Menu Options Section
        MenuItem(
            icon = Icons.Default.Star,
            label = "Chat",
            onClick = { onNavigate(Screen.Chat) }
        )
        MenuItem(
            icon = Icons.Default.Star,
            label = "Interests",
            onClick = { /* Placeholder for future navigation */ }
        )
        MenuItem(
            icon = Icons.Default.Settings,
            label = "Settings",
            onClick = { /* Placeholder for future navigation */ }
        )
        MenuItem(
            icon = Icons.Default.Person,
            label = "Avatar",
            onClick = { onNavigate(Screen.Avatar) }
        )
        MenuItem(
            icon = Icons.Default.Person,
            label = "Explore",
            onClick = { /* Placeholder for future navigation */ }
        )
    }
}

@Composable
fun MenuItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp) // Adjust the size of the circle here
                .background(Color(0xFF1C546D), shape = CircleShape) // Background color and shape
                .padding(8.dp) // Optional padding to make the icon smaller within the circle
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(24.dp) // Size of the icon itself
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
