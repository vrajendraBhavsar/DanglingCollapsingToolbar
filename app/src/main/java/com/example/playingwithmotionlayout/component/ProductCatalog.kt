/*
package com.example.playingwithmotionlayout.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.model.Item
import com.example.playingwithmotionlayout.component.management.ToolbarState
import com.example.playingwithmotionlayout.model.ListPreviewParameterProvider
import com.example.playingwithmotionlayout.ui.theme.PlayingWithMotionLayoutTheme
import com.example.scrollflags.ExitUntilCollapsedState

private val MinToolbarHeight = 96.dp
val MaxToolbarHeight = 176.dp

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(toolbarHeightRange)
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProductCatalog(
    item: List<Item>,
    columns: Int,
    modifier: Modifier = Modifier
) {

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }
    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val scrollState = rememberScrollState()

    toolbarState.scrollValue = scrollState.value

    //...Motion layout
    val context = LocalContext.current  //to get the raw file, we need context.
    Log.d("TAG", "ProfileHeader: progress => ${toolbarState.progress}")
    val motionScene = remember {    // To include raw file, the JSON5 script file
        context.resources.openRawResource(com.example.playingwithmotionlayout.R.raw.motion_scene_netflix)
            .readBytes()
            .decodeToString()   //readBytes -> cuz we want motionScene in String
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = toolbarState.progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val propertiesOfContentImage = motionProperties(id = "content_img")   //motionProperties -> to get the custom properties

        Box(modifier = modifier) {
            */
/**
             * Collapsing Toolbar
             *//*

            */
/*CollapsingToolbar(
                backgroundImageResId = R.drawable.ic_starwars,
                progress = toolbarState.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("collapsing_box")
                    .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                    .graphicsLayer { translationY = toolbarState.offset }
                    .zIndex(1f)
            )*//*

            */
/**
             * Grid list
             *//*

            GridItemHandler(
                list = item,
                columns = columns,
                modifier = Modifier
                    .layoutId("data_content")
                    .zIndex(0f),
//                scrollState = scrollState,
                contentPadding = PaddingValues(top = MaxToolbarHeight)
            )
            */
/*Toolbar(
                progress = toolbarState.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(with(LocalDensity.current) { toolbarState.height.toDp()})
                    .graphicsLayer { translationY = toolbarState.offset }
            )*//*



            */
/*Image(
                painter = painterResource(id = R.drawable.ic_baby_yoda),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
                    .layoutId("content_img"),
                contentDescription = "Content image holder"
            )*//*

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    PlayingWithMotionLayoutTheme() {
        ProductCatalog(
            item = list,
            columns = 2,
            modifier = Modifier.fillMaxSize()
        )
    }
}*/
