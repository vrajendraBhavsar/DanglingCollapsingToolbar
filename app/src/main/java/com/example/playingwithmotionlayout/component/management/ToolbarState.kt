package com.example.playingwithmotionlayout.component.management

import androidx.compose.runtime.Stable

@Stable
interface ToolbarState {
    val offset: Float
    val height: Float
    val progress: Float
    var scrollValue: Int
}