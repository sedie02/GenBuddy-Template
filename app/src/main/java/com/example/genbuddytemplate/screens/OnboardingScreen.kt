package com.example.genbuddytemplate.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genbuddytemplate.Screen
import com.example.genbuddytemplate.R
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.geometry.Offset
import androidx.compose.animation.core.animateFloatAsState

@Composable
fun OnboardingScreen(onNavigate: (Screen) -> Unit) {

    // State management for current phase index
    var currentPhaseIndex by remember { mutableStateOf(0) }

    // Define the text, images, and title bar images for each phase
    val texts = listOf(
        "Your personal AI assistant",
        "Gives advice based on personal context",
        "Proactively creates prompts for you",
        "Always available to answer questions"
    )

    val images = listOf(
        R.drawable.firstscreenimage,
        R.drawable.secondscreenimage,
        R.drawable.thirdscreenimage,
        R.drawable.fourthscreenimage
    )

    val titleBarImages = listOf(
        R.drawable.titlebars,
        R.drawable.titlebars2,
        R.drawable.titlebars3,
        R.drawable.titlebars4
    )

    // Define font family for text
    val anekDevanagariBold = FontFamily(
        Font(R.font.anek_devanagari_bold, FontWeight.Bold)
    )

    // Handle the "Next" button click
    fun changeTextAndImage(isNext: Boolean) {
        currentPhaseIndex = (currentPhaseIndex + if (isNext) 1 else -1 + texts.size) % texts.size
    }

    // Root Layout
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Background Image
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF006494), // Top color (start color)
                            Color(0xFF13293D)  // Bottom color (end color)
                        ),
                        start = Offset(0f, 0f), // Start of the gradient (top-left)
                        end = Offset(0f, Float.POSITIVE_INFINITY) // End of the gradient (bottom)
                    )
                )
                .padding(16.dp)
        )

        Column(
            modifier = Modifier
                .width(296.dp)
                .height(699.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title Text with a fade animation
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(tween(500)),
                exit = fadeOut(tween(500))
            ) {
                Text(
                    text = "Gen Buddy",
                    style = TextStyle(
                        color = colorResource(id = R.color.genbuddy_title_text),
                        fontSize = 78.sp,
                        lineHeight = 78.sp * 0.54, // Matches lineSpacingMultiplier="0.54"
                        fontFamily = anekDevanagariBold
                    ),
                    modifier = Modifier
                        .width(230.dp)
                        .height(170.dp)
                        .align(Alignment.Start)
                )
            }

            // Subtitle Text (Dynamic with swipe animation)
            val translationX: Float by animateFloatAsState(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500)
            )

            AnimatedVisibility(
                visible = true,
                enter = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)),
                exit = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500))
            ) {
                Text(
                    text = texts[currentPhaseIndex],
                    style = TextStyle(
                        color = colorResource(id = R.color.genbuddy_subtitle_text),
                        fontSize = 33.sp,
                        lineHeight = 33.sp * 0.9, // Matches lineSpacingMultiplier="0.9"
                        fontFamily = anekDevanagariBold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Image with slide animation
            val slideIn = slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500))

            AnimatedVisibility(
                visible = true,
                enter = slideIn,
                exit = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[currentPhaseIndex]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(297.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title Bar Image (Dynamic)
            Image(
                painter = painterResource(id = titleBarImages[currentPhaseIndex]),
                contentDescription = null,
                modifier = Modifier
                    .width(211.dp)
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Next Button
            Button(
                onClick = {
                    if (currentPhaseIndex < texts.size - 1) {
                        changeTextAndImage(isNext = true)
                    } else {
                        onNavigate(Screen.Login)  // Final screen, navigate to next
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F1F2)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = if (currentPhaseIndex == texts.size - 1) "Get Started" else "Next",
                    style = TextStyle(
                        color = colorResource(id = R.color.black),
                        fontSize = 28.sp,
                        fontFamily = anekDevanagariBold
                    )
                )
            }
        }
    }
}
