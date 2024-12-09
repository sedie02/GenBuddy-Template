package com.example.genbuddytemplate.screens.mainassets
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen

// Composable function for the chat screen
@Composable
fun ChatScreen(onNavigate: (Screen) -> Unit) {
    // State variables to store messages and the text input
    var messages by remember { mutableStateOf(listOf<String>()) } // To store chat messages
    var textState by remember { mutableStateOf(TextFieldValue("")) } // To manage input field text

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13293D))
    ) {
        // Display messages in a vertical column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Iterate over each message in the list and display as a TextBubble
            for (message in messages) {
                TextBubble(message = message)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Input area for typing and sending messages
        ChatInput(
            textState = textState,
            onTextChange = { textState = it },
            onSend = {
                // When the send button is clicked, add the message to the list
                if (textState.text.isNotBlank()) {
                    messages = messages + textState.text
                    textState = TextFieldValue("") // Clear input field after sending
                }
            }
        )
    }
}

// Composable for displaying a single message in a text bubble
@Composable
fun TextBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Color.White, shape = RoundedCornerShape(8.dp)) // Message bubble shape
            .padding(12.dp)
    ) {
        Text(
            text = message,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

// Composable for the text input field and send button at the bottom
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
        // BasicTextField to capture the input from the user
        BasicTextField(
            value = textState,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .background(Color(0xFF384756), shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White) // White text color
        )
        Spacer(modifier = Modifier.width(8.dp))

        // Send button that triggers the send action
        Button(
            onClick = onSend,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF384756) // Button color
            )
        ) {
            Text("Send", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
    // Display a sample chat screen
    ChatScreen(onNavigate = { /* No-op for preview */ })
}
