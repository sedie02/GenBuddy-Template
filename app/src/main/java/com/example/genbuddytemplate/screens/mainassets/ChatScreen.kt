package com.example.genbuddytemplate.screens.mainassets
import android.util.Log
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen
import com.example.genbuddytemplate.screens.HttpClientProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


@Composable
fun ChatScreen(onNavigate: (Screen) -> Unit) {
    val httpClient = HttpClientProvider.httpClient
    val scope = rememberCoroutineScope()

    // State variables to store messages and the text input
    var messages by remember { mutableStateOf(listOf<String>()) }
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    @Serializable
    data class MessageRequest(val message: String)

    @Serializable
    data class MessageResponse(
        val response_message: String,
        val user_message: String
    )

    // Function to send a message to the API and get a response
    fun sendMessageToApi(
            httpClient: HttpClient,
    text: String,
    onMessageReceived: (MessageResponse) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d("ChatScreen", "Sending message to API: $text") // Log before making the request

                val responseData: MessageResponse = httpClient.post("http://192.168.2.10:5000/chat") {
                    contentType(ContentType.Application.Json)
                    setBody(MessageRequest(message = text))
                }.body() // Automatically deserialize the response

                Log.d("ChatScreen", "Response received: $responseData") // Log after receiving the response

                onMessageReceived(responseData)
            } catch (e: Exception) {
                Log.e("ChatScreen", "Error sending message: ${e.localizedMessage}", e) // Log the error
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13293D))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            for (message in messages) {
                TextBubble(message = message)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        ChatInput(
            textState = textState,
            onTextChange = { textState = it },
            onSend = {
                println("Send button clicked")
                if (textState.text.isNotBlank()) {
                    val userMessage = textState.text
                    println("User message to send: $userMessage")
                    messages = messages + "You: $userMessage"
                    textState = TextFieldValue("") // Clear input field

                    // Call sendMessageToApi with the HttpClient and the user's message
                    sendMessageToApi(httpClient, userMessage) { responseMessage ->
                        println("Received response message: ${responseMessage.response_message}")
                        messages = messages + "Bot: ${responseMessage.response_message}"
                    }
                }
            }
        )

    }
}

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
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

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
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF384756)
            )
        ) {
            Text("Send", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatScreen() {
    ChatScreen(onNavigate = { /* No-op for preview */ })
}