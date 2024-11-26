package com.example.genbuddytemplate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen

//oude test code om te wisseln tussen de 3 main schermen
@Composable
fun MainChatScreen2(onNavigate: (Screen) -> Unit) {
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

@Composable
fun MainChatScreen(onNavigate: (Screen) -> Unit) {
    var messages by remember { mutableStateOf(listOf<String>()) } // To store chat messages
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Custom top bar
        CustomTopBar()

        // Chat area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color(0xFF13293D)) // Background for chat area
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                // Display messages as bubbles
                for (message in messages) {
                    TextBubble(message = message)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // Input area
        ChatInput(
            textState = textState,
            onTextChange = { textState = it },
            onSend = {
                if (textState.text.isNotBlank()) {
                    messages = messages + textState.text // Add new message to the list
                    textState = TextFieldValue("") // Clear input field
                }
            }
        )
    }
}

// hamburger menu icon, user icon and Genbuddy text at the top
@Composable
fun CustomTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF13293D)) // Top bar color
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* Placeholder for future menu action */ }) {
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
        IconButton(onClick = { /* Placeholder for future user action */ }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User",
                tint = Color.White
            )
        }
    }
}

// textfield and send button at the bottom of the screen
@Composable
fun ChatInput(
    textState: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = textState,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFF384756), shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onSend,
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF384756)
            )
        ) {
            Text("Send")
        }
    }
}

// Textbubbles after a message is sent
@Composable
fun TextBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Text(
            text = message,
            color = Color.Black
        )
    }
}
