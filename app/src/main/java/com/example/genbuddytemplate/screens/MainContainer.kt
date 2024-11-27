package com.example.genbuddytemplate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.genbuddytemplate.Screen
import com.example.genbuddytemplate.components.DrawerContent
import com.example.genbuddytemplate.components.CustomTopBar
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.genbuddytemplate.screens.mainassets.AvatarScreen
import com.example.genbuddytemplate.screens.mainassets.ChatScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(onNavigate: (Screen) -> Unit) {
    // Drawer and coroutine state
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Track the current screen to display (start with ChatScreen)
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Chat) }

    // The Modal Navigation Drawer that holds the top bar and content area
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onNavigate = { screen ->
                    when (screen) {
                        Screen.Chat -> currentScreen = Screen.Chat // Show ChatScreen
                        Screen.Avatar -> currentScreen = Screen.Avatar // Show AvatarScreen
                        else -> currentScreen = Screen.Chat // Default to ChatScreen
                    }
                    coroutineScope.launch { drawerState.close() } // Close the drawer after selection
                }
            )
        }
    ) {
        // Main content area wrapped by the drawer
        Column(modifier = Modifier.fillMaxSize()) {

            // Top bar with hamburger menu and app title
            CustomTopBar(
                onMenuClick = {
                    coroutineScope.launch { drawerState.open() } // Open drawer on menu click
                },
                onNavigate = onNavigate
            )

            // Main content area where the screen will be dynamically swapped
            Box(
                modifier = Modifier
                    .weight(1f) // Content takes the remaining space
                    .fillMaxWidth()
            ) {
                // Dynamically swap between screens based on the currentScreen state
                when (currentScreen) {
                    Screen.Chat -> {
                        ChatScreen(onNavigate = onNavigate) // Show ChatScreen
                    }
                    Screen.Avatar -> {
                        AvatarScreen(onNavigate = onNavigate) // Show AvatarScreen
                    }
                    else -> {
                        ChatScreen(onNavigate = onNavigate) // Default to ChatScreen if no match
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContainer() {
    MainContainer(onNavigate = {})
}
