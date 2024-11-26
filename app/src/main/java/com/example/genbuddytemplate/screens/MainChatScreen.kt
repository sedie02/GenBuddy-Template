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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.*
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope

// Oude test code om te wisselen tussen de 3 main schermen
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainChatScreen(onNavigate: (Screen) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ) {
        ChatContent(
            drawerState = drawerState,
            coroutineScope = coroutineScope,
            onNavigate = onNavigate
        )
    }
}

@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.75f) // Drawer width: 75% of the screen
            .background(Color(0xFF1E2D40))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Menu Option 1",
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = "Menu Option 2",
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        // Add more menu options as needed
    }
}

// hamburger menu icon, user icon and Genbuddy text at the top
@Composable
fun CustomTopBar(onMenuClick: () -> Unit) {
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
            .background(Color(0xFF13293D))
            .padding(start = 15.dp, top = 8.dp, end = 8.dp, bottom = 25.dp),
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


@Composable
fun ChatContent(
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    onNavigate: (Screen) -> Unit
) {
    var messages by remember { mutableStateOf(listOf<String>()) } // To store chat messages
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTopBar(onMenuClick = {
            coroutineScope.launch { drawerState.open() } // Open the drawer when the menu icon is clicked
        })

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

@Preview(showBackground = true)
@Composable
fun PreviewMainChatScreen() {
    var sampleMessages = listOf("Hello!", "How are you?", "Welcome to the chat!")
    MainChatScreen(onNavigate = {}) // Provide an empty lambda for navigation
}

