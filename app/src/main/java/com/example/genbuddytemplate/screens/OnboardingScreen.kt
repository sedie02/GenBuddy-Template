package com.example.genbuddytemplate.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset

@Composable
fun OnboardingScreen(onNavigate: (Screen) -> Unit) {

    //State management for current phase index
    var currentPhaseIndex by remember { mutableIntStateOf(0) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isContentVisible by remember { mutableStateOf(true) }


    //Text for each phase
    val texts = listOf(
        "Your personal AI assistant",
        "Gives advice based on personal context",
        "Proactively creates prompts for you",
        "Always available to answer questions"
    )

    //Images for each phase
    val images = listOf(
        R.drawable.firstscreenimage,
        R.drawable.secondscreenimage,
        R.drawable.thirdscreenimage,
        R.drawable.fourthscreenimage
    )

    //Images for the bars for each phase
    val titleBarImages = listOf(
        R.drawable.titlebars,
        R.drawable.titlebars2,
        R.drawable.titlebars3,
        R.drawable.titlebars4
    )

    //Modifier for swiping
    val swipeModifier = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures(
            onDragEnd = {
                //Allow swiping to the left (next phase) if not at the last phase
                if (offsetX < -300 && currentPhaseIndex < texts.size - 1) {
                    isContentVisible = false
                    currentPhaseIndex++
                    offsetX = 0f
                    isContentVisible = true
                }
                //Allow swiping to the right (previous phase) if not at the first phase
                else if (offsetX > 300 && currentPhaseIndex > 0) {
                    isContentVisible = false
                    currentPhaseIndex--
                    offsetX = 0f
                    isContentVisible = true
                } else {
                    //Reset offset if swipe threshold not reached
                    offsetX = 0f
                }
            },
            onHorizontalDrag = { _, dragAmount ->
                //Update the offset during the swipe
                offsetX += dragAmount
                //If first phase or last phase, disable swipe to left or right depending on which phase
                if ((currentPhaseIndex == texts.size - 1 && offsetX < 0) || (currentPhaseIndex == 0 && offsetX > 0)) {
                    offsetX = 0f
                }
            }
        )
    }

    //Define font family for text
    val anekDevanagariBold = FontFamily(
        Font(R.font.anek_devanagari_bold)
    )

    //Define font family for text
    val ebGaramond = FontFamily(
        Font(R.font.eb_garamond)
    )

    //When clicking the "Next" button, change images
    fun changeTextAndImage(isNext: Boolean) {
        currentPhaseIndex = (currentPhaseIndex + if (isNext) 1 else -1 + texts.size) % texts.size
    }

    //Go back one phase when pressing the back system button
    BackHandler(enabled = currentPhaseIndex > 0) {
        currentPhaseIndex--
    }

    //Root Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(swipeModifier),
        contentAlignment = Alignment.Center
    ) {
        //Background Gradient
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF006494),
                            Color(0xFF13293D)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                )
                .padding(16.dp)
        )
        //Added columns
        Column(
            modifier = Modifier
                .width(325.dp)
                .height(699.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Title Text
            Text(
                text = "Gen Buddy",
                style = TextStyle(
                    color = colorResource(id = R.color.genbuddy_title_text),
                    fontSize = 78.sp,
                    lineHeight = 78.sp * 0.9,
                    fontFamily = anekDevanagariBold
                ),
                modifier = Modifier
                    .width(325.dp)
                    .height(170.dp)
                    .align(Alignment.Start)
            )

            //Subtext
            Text(
                text = texts[currentPhaseIndex],
                style = TextStyle(
                    color = colorResource(id = R.color.genbuddy_subtitle_text),
                    fontSize = 33.sp,
                    fontFamily = ebGaramond
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset { IntOffset(offsetX.toInt(), 0) }
            )

            //Adds empty space
            Spacer(modifier = Modifier.height(16.dp))

            //Main Image
            Image(
                painter = painterResource(id = images[currentPhaseIndex]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(285.dp)
                    .offset { IntOffset(offsetX.toInt(), 0) }
            )

            //Adds empty space
            Spacer(modifier = Modifier.height(16.dp))

            //Phase Bar Images
            Image(
                painter = painterResource(id = titleBarImages[currentPhaseIndex]),
                contentDescription = null,
                modifier = Modifier
                    .width(211.dp)
                    .wrapContentHeight()
            )

            //Adds empty space
            Spacer(modifier = Modifier.height(32.dp))

            //Next Button
            Button(
                onClick = {
                    //If not the last phase
                    if (currentPhaseIndex < texts.size - 1) {
                        changeTextAndImage(isNext = true)
                    }
                    //If last phase
                    else {
                        //Navigate to the next screen (Login Screen)
                        onNavigate(Screen.Login)
                    }
                },
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE8F1F2)),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                //Change text in the button to "Get Started" if the last phase, otherwise the button says "Next"
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
