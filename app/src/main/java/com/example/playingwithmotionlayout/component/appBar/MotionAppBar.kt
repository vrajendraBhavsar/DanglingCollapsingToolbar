package com.example.playingwithmotionlayout.component.appBar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.playingwithmotionlayout.R
import com.example.playingwithmotionlayout.common.isScrolled
import com.example.playingwithmotionlayout.ui.theme.LeafyGreen
import java.lang.reflect.Array.get

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionAppBar(lazyScrollState: LazyListState) {
    val context = LocalContext.current

    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 0f else 1f,
        tween(1000)
    )
//    val progress: Float
//    get() = 1 - (maxHeight - height) / rangeDifference
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene_netflix).readBytes().decodeToString()
    }
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 300.dp else 60.dp,
        tween(1000)
    )
    //Worked code
    /*val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.isScrolled) 60.dp else 300.dp,
        tween(1000)
    )*/

    // handle composables within motion layout just like xml, first is always on bottom

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .background(LeafyGreen) //Extra space below the image
//                .animateContentSize(animationSpec = tween(durationMillis = 300))
                .height(motionHeight)
        ) {

            val boxProperties = motionProperties(id = "collapsing_box")
            val roundedShape = RoundedCornerShape(
                bottomStart = boxProperties.value.int("roundValue").dp,
                bottomEnd = boxProperties.value.int("roundValue").dp
            )

            /**
             * bg-box
             **/
            /*Box(
                modifier = Modifier
                    .layoutId("collapsing_box")
                    .clip(roundedShape)
                    *//*.background(
                        brush = Brush.verticalGradient(
                            colors,
                            endY = 350f
                        )
                    )*//*
            )*/

            /**
             * bg-image
             **/
            Image(
                painter = painterResource(id = R.drawable.ic_topbar_minion),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .layoutId("collapsing_box")
                    .clip(roundedShape)
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = 1f
                    },
                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            )

            /**
             * Text - Collapsing
             */
            val motionTextProperties = motionProperties(id = "motion_text")

            Text(
                text = stringResource(id = R.string.collapsing_text_star_wars_IX),
                color = motionTextProperties.value.color("textColor"),
//                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("motion_text")
            )

            /**
             * Main image
             **/
            Image(
                painter = painterResource(id = R.drawable.ic_teddy_minion),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .layoutId("content_img")
                    .size(width = 72.dp, height = 92.dp)
                    .clip(RoundedCornerShape(5.dp)),
//                    .zIndex(2f),
                contentDescription = "Content image holder"
            )
        }
    }
}