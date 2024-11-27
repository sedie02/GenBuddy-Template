package com.example.genbuddytemplate.navigation

// Sealed class for the screens with a route property
sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding_screen")
    object Login : Screen("login_screen")
    object MainContainer : Screen("main_container_screen")
}
