package com.example.genbuddytemplate.screens.mainassets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.genbuddytemplate.Screen

@Composable
fun AvatarScreen(onNavigate: (Screen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13293D))

    ) {
        // Top bar with the word "AVATAR"
        Text(
            text = "AVATAR",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF006495)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Display the avatar attributes
        AttributeCard(
            title = "Kees",
            undertitle = "wazebi",
            icon = Icons.Default.Star
        )
        Spacer(modifier = Modifier.height(16.dp))

        AttributeCard(
            title = "Producer",
            undertitle = "Sedie-Man",
            icon = Icons.Default.Star
        )
        Spacer(modifier = Modifier.height(16.dp))

        AttributeCard(
            title = "Music Lover",
            undertitle = "Jazz, Blues, Rock",
            icon = Icons.Default.Star
        )
    }
}

@Composable
fun AttributeCard(title: String, undertitle: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF006495), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Title and Undertitle
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = undertitle,
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }

        // Icon on the right side
        Icon(
            imageVector = icon,
            contentDescription = "Attribute Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAvatarScreen() {
    AvatarScreen(onNavigate = {})
}
