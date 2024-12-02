package com.example.genbuddytemplate.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import com.example.genbuddytemplate.Screen

@Composable
fun RegisterScreen(onNavigate: (Screen) -> Unit) {
    var fullname by remember { mutableStateOf("") }
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

            // Fullname TextField
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
                            Text(
                                text = "Fullname",
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))

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
            Spacer(modifier = Modifier.height(16.dp))

            // Password Requirements
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "• Minimum 8 characters", color = Color.Gray, fontSize = 12.sp)
                Text(text = "• At least one letter", color = Color.Gray, fontSize = 12.sp)
                Text(text = "• At least one special character (!@#$%^&*)", color = Color.Gray, fontSize = 12.sp)
                Text(text = "• At least one number", color = Color.Gray, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Sign Up Button
            Button(
                onClick = { /* Handle sign-up */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Sign Up")
            }
            Spacer(modifier = Modifier.height(32.dp))

            // OR Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(color = Color.Gray, modifier = Modifier.weight(1f))
                Text(text = "  or  ", color = Color.White)
                Divider(color = Color.Gray, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(32.dp))

            // Social Login Options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialLoginButton(text = "G")
                SocialLoginButton(text = "W")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(onNavigate = {})
}
