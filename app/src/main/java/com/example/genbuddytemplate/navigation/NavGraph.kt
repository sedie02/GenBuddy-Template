package com.example.genbuddytemplate.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.genbuddytemplate.screens.OnboardingScreen
import com.example.genbuddytemplate.screens.LoginScreen
import com.example.genbuddytemplate.screens.MainContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {

        // Onboarding Screen
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onNavigate = { screen ->
                navController.navigate("onboarding_screen")
            })
        }

        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(onNavigate = { screen ->
                navController.navigate("login_screen")
            })
        }

        // Main Container (which includes the ChatScreen)
        composable(Screen.MainContainer.route) {
            MainContainer(onNavigate = { screen ->
                navController.navigate("main_container_screen")
            })
        }
    }
}
