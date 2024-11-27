package com.example.genbuddytemplate.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.genbuddytemplate.Screen
import com.example.genbuddytemplate.components.DrawerContent
import com.example.genbuddytemplate.components.CustomTopBar
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.genbuddytemplate.screens.mainassets.ChatScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer(onNavigate: (Screen) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(onNavigate)
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

            // Chat content area, replaced with ChatScreen for chat functionality
            Box(
                modifier = Modifier
                    .weight(1f) // Chat content takes the remaining space
                    .fillMaxWidth()
            ) {
                ChatScreen(onNavigate = onNavigate) // Use ChatScreen for actual chat content
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainContainer() {
    MainContainer(onNavigate = {})
}
