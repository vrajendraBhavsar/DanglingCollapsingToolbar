package com.example.playingwithmotionlayout.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.playingwithmotionlayout.ui.theme.PlayingWithMotionLayoutTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingWithMotionLayoutTheme {
                MainScreenContent()
            }
        }
    }
}
