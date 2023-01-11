package com.example.playingwithmotionlayout.common

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp


val LazyListState.isScrolled: Boolean
get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0