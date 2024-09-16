package com.example.firstexperiment.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstexperiment.R
import com.example.firstexperiment.presentation.ui.theme.YellowBase

@Composable
fun HomeScreen() {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .padding(bottom = 64.dp)
    ) {
        HomeScreenText()
        LandingPageImage()
        HomeScreenSimpleButton()
    }
}

@Composable
fun LandingPageImage() {
    Image(
        painter = painterResource(id = R.drawable.ic__d_glasses_bro__1_),
        contentDescription = "Landing Page Image",
        modifier = Modifier
            .padding(horizontal = 10.dp)
    )
}

@Composable
fun HomeScreenSimpleButton() {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = YellowBase,
            contentColor = Color.Black
        )
    ) {
        Text(text = "Explore More!", fontSize = 15.sp)
    }
}

@Composable
fun HomeScreenText() {
    val fontFamily = FontFamily(
        Font(R.font.lexend_light)
    )

    Text(
        text = "Welcome to CinemaDB!",
        color = androidx.compose.ui.graphics.Color.Black,
        fontSize = 24.sp,
        fontFamily = fontFamily,
        modifier = Modifier.padding(vertical = 0.dp)
    )
}