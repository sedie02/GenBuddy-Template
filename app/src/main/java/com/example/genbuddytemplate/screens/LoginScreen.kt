package com.example.genbuddytemplate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.genbuddytemplate.Screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun LoginScreen(onNavigate: (Screen) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13293D)), // Achtergrondkleur: rgb(19, 41, 61)
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.Start, // Links uitlijnen
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 60.dp) // Voeg padding bovenaan toe
                .fillMaxWidth()
        ) {
            // Titeltekst
            Text(
                text = "GenBuddy",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start // Links uitlijnen
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please Log in to your account",
                color = Color(0xFF959DAB),
                fontSize = 14.sp,
                textAlign = TextAlign.Start // Links uitlijnen
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Email TextField
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
                            Text(
                                text = "Email",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Password TextField
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
                            Text(
                                text = "Password",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Remember Me and Forgot Password Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = { /* handle remember me state */ },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color.White,
                            uncheckedColor = Color.Gray
                        )
                    )
                    Text(text = "Remember", color = Color.White, fontSize = 12.sp)
                }
                Text(
                    text = "Forgot password?",
                    color = Color(0xFF3C7DFE),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { /* Navigate to forgot password */ }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

// Login Button
            Button(
                onClick = { onNavigate(Screen.MainChat) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, // Witte achtergrond
                    contentColor = Color(0xFF13293D) // Tekstkleur donkerblauw
                ),
                shape = RoundedCornerShape(4.dp) // Minder ronde hoeken
            ) {
                Text(text = "Log In")
            }
            Spacer(modifier = Modifier.height(32.dp)) // Vergrote ruimte tussen knop en "OR"

// OR Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(color = Color.Gray, modifier = Modifier.weight(1f))
                Text(text = "  or  ", color = Color.White)
                Divider(color = Color.Gray, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(32.dp)) // Vergrote ruimte tussen "OR" en social buttons

// Social Login Options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialLoginButton(text = "G")
                SocialLoginButton(text = "W")
            }
            Spacer(modifier = Modifier.height(64.dp)) // Extra ruimte tussen social buttons en "Sign Up"

// Sign Up Text
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Horizontaal centreren
            ) {
                Text(text = "Don't have an account?", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Sign Up",
                    color = Color(0xFF3C7DFE),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { /* Navigate to sign-up */ }
                )
            }

        }
    }
}

// Social Login Button
@Composable
fun SocialLoginButton(text: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(Color(0xFF384757), shape = MaterialTheme.shapes.small), // rgb(56, 71, 87)
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onNavigate = {})
}
