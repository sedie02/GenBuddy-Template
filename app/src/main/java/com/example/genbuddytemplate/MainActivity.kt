package com.example.genbuddytemplate
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.genbuddytemplate.ui.theme.GenBuddyTemplateTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.genbuddytemplate.screens.OnboardingScreen
import com.example.genbuddytemplate.screens.LoginScreen
import com.example.genbuddytemplate.screens.MainContainer
import com.example.genbuddytemplate.screens.mainassets.AvatarScreen
import com.example.genbuddytemplate.screens.mainassets.ChatScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    // State to manage current screen
    var currentScreen by remember { mutableStateOf(Screen.Onboarding) }

    when (currentScreen) {
        Screen.Onboarding -> OnboardingScreen(onNavigate = { currentScreen = it })
        Screen.Login -> LoginScreen(onNavigate = { currentScreen = it })
        Screen.MainChat -> MainContainer(onNavigate = { currentScreen = it })
        Screen.Avatar -> AvatarScreen ( onNavigate = { currentScreen = it })
        Screen.Chat -> ChatScreen ( onNavigate = { currentScreen = it })
    }
}

// Enum class to define screens
enum class Screen {
    Onboarding, Login, MainChat, Avatar, Chat
}