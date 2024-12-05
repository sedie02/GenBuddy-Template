package com.example.genbuddytemplate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen

@Composable
fun RegisterScreen(onNavigate: (Screen) -> Unit) {
    var fullname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val minLengthMet = password.length >= 8
    val hasLetter = password.any { it.isLetter() }
    val hasSpecialChar = password.any { "!@#$%^&*".contains(it) }
    val hasNumber = password.any { it.isDigit() }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun handleSignUp() {
        if (fullname.isBlank()) {
            // Toon foutmelding: naam is vereist
            return
        }
        if (!isValidEmail(email)) {
            // Toon foutmelding: ongeldig e-mailadres
            return
        }
        if (!(minLengthMet && hasLetter && hasSpecialChar && hasNumber)) {
            // Toon foutmelding: wachtwoordvereisten niet voldaan
            return
        }
        // Navigeren naar volgende scherm als alles correct is
        onNavigate(Screen.Login)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13293D)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 60.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "GenBuddy",
                color = Color.White,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Create your account",
                color = Color(0xFF959DAB),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Fullname Field
            BasicTextField(
                value = fullname,
                onValueChange = { fullname = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF384757), shape = MaterialTheme.shapes.small)
                    .padding(16.dp),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (fullname.isEmpty()) {
                            Text("Fullname", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Email Field
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF384757), shape = MaterialTheme.shapes.small)
                    .padding(16.dp),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (email.isEmpty()) {
                            Text("Email", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Password Field
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF384757), shape = MaterialTheme.shapes.small)
                    .padding(16.dp),
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (password.isEmpty()) {
                            Text("Password", color = Color.Gray)
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password Requirements with Dynamic Indicators
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "• Minimum 8 characters",
                    color = if (minLengthMet) Color.Green else Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "• At least one letter",
                    color = if (hasLetter) Color.Green else Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "• At least one special character (!@#$%^&*)",
                    color = if (hasSpecialChar) Color.Green else Color.Gray,
                    fontSize = 12.sp
                )
                Text(
                    text = "• At least one number",
                    color = if (hasNumber) Color.Green else Color.Gray,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { handleSignUp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account?", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    "Log In",
                    color = Color(0xFF3C7DFE),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { onNavigate(Screen.Login) }
                )
            }
        }
    }
}
