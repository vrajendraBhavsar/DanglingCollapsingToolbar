package com.example.playingwithmotionlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.model.Item
import com.example.playingwithmotionlayout.component.*
import com.example.playingwithmotionlayout.ui.theme.DarkPurple
import com.example.playingwithmotionlayout.ui.theme.PlayingWithMotionLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingWithMotionLayoutTheme {
                val lazyScrollState = rememberLazyListState()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        MotionAppBar(lazyScrollState)
                    }
                ) {
                    /*LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .animateContentSize(),
                        state = lazyScrollState
                    ) {
                        itemsIndexed(repos) { index, item ->
                            //3. List item
                            RepoItem(repo = item)
                            *//*ProductCatalog(
                                item = populateList(),
                                columns = 2)*//*
                            if (index != repos.lastIndex)
                                Divider(modifier = Modifier.padding(8.dp))
                        }
                    }*/
                    //...
                    /*Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                            .animateContentSize()
                    ) {
                        ProductCatalog(
                            item = populateList(),
                            columns = 2,
                        )
                    }*/
                    //...
                    val scrollState = rememberScrollState()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(DarkPurple)    // bg color of the Grid content
                            .animateContentSize(),
                        state = lazyScrollState
                    ) {
                        itemsIndexed(populateList()) { index, item ->
                            //3. List item
//                            RepoItem(repo = item)
                            GridItemHandler(
                                list = populateList(),
                                columns = 2,
                                modifier = Modifier
                                    .layoutId("data_content")
                                    .zIndex(0f),
//                                scrollState = scrollState,
                                contentPadding = PaddingValues(top = MaxToolbarHeight)
                            )
//                            if (index != repos.lastIndex)
//                                Divider(modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionAppBar(lazyScrollState: LazyListState) {
    val Alpha = 0.75f

    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene_netflix).readBytes().decodeToString()
    }

    val progress by animateFloatAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 0f else 1f,
        tween(500)
    )
    val motionHeight by animateDpAsState(
        targetValue = if (lazyScrollState.firstVisibleItemIndex in 0..1) 250.dp else 30.dp,
        tween(500)
    )

    // handle composables within motion layout just like xml, first is always on bottom

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color.Cyan) //Extra space below the image
                .height(motionHeight)
        ) {

            val boxProperties = motionProperties(id = "box")
            val roundedShape = RoundedCornerShape(
                bottomStart = boxProperties.value.int("roundValue").dp,
                bottomEnd = boxProperties.value.int("roundValue").dp
            )
            /**
             * bg-image
             **/
            /*Image(
                painter = painterResource(id = R.drawable.design),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(roundedShape)
                    .layoutId("box_image")
            )*/
            //...........
            /*Image(
                painter = painterResource(id = backgroundImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = progress * Alpha
                    },
                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            )*/

            /*Box(    //Top bar collapsing Box/ bg-image
                modifier = Modifier
                    .layoutId("collapsing_box")
                    .clip(roundedShape)
                    *//*.paint(
                        painterResource(id = R.drawable.ic_starwars)
                    )*//*
                    .graphicsLayer {
                        alpha = progress * Alpha
                    },
                contentAlignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            ) {*/
            Image(
                painter = painterResource(id = R.drawable.ic_starwars),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = progress * Alpha
                    },
                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
            )
//            }

            /**
             * Sub image
             **/
            /*Image(
                painter = painterResource(id = R.drawable.compose_icon),
                contentDescription = null,
                modifier = Modifier
                    .layoutId("weapon_icon")
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.White,
                                Color.Transparent
                            )
                        )
                    )
            )*/
            /**
             * Text - Collapsing
             */
            val motionTextProperties = motionProperties(id = "motion_text")

            /*Text(
                text = "Astro",
                fontSize = 24.sp,
                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.layoutId("user_name")
            )*/

            Text(
                text = stringResource(id = R.string.collapsing_text_star_wars_IX),
                color = motionTextProperties.value.color("textColor"),
                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
                modifier = Modifier.layoutId("motion_text")
            )
            /**
             * Main image
             **/
            /*Image(
                painter = painterResource(id = R.drawable.ic_darth_vader),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .layoutId("user_image")
            )*/
            //...
            Image(
                painter = painterResource(id = R.drawable.ic_darth_vader),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .layoutId("content_img")
                    .size(width = 50.dp, height = 70.dp),
//                    .zIndex(2f),
                contentDescription = "Content image holder"
            )
        }
    }

    //OLD ---
    /*Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan) //Extra space below the image
                .height(motionHeight)
        ) {

            val boxProperties = motionProperties(id = "box")
            val roundedShape = RoundedCornerShape(
                bottomStart = boxProperties.value.int("roundValue").dp,
                bottomEnd = boxProperties.value.int("roundValue").dp
            )

            Box(    //Top bar collapsing Box/ bg-image
                modifier = Modifier
                    .layoutId("box")
                    .clip(roundedShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors,
                            endY = 350f
                        )
                    )
            )
            */
    /**
     * bg-image
     **//*
            Image(
                painter = painterResource(id = R.drawable.design),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(roundedShape)
                    .layoutId("box_image")
            )

            */
    /**
     * Sub image
     **//*
            Image(
                painter = painterResource(id = R.drawable.compose_icon),
                contentDescription = null,
                modifier = Modifier
                    .layoutId("weapon_icon")
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.White,
                                Color.Transparent
                            )
                        )
                    )
            )

            Text(
                text = "Astro",
                fontSize = 24.sp,
                fontWeight = if (progress == 1f) FontWeight.Light else FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.layoutId("user_name")
            )
            */
    /**
     * Main image
     **//*
            Image(
                painter = painterResource(id = R.drawable.ic_darth_vader),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .layoutId("user_image")
            )
        }
    }*/
}

fun populateList(): List<Item> {
    return listOf(
        Item("The Godfather", itemImage = R.drawable.ic_darth_vader),
        Item("The Shawshank Redemption", itemImage = R.drawable.ic_darth_vader),
        Item("Schindler's List", itemImage = R.drawable.ic_darth_vader),
        Item("Casablanca ", itemImage = R.drawable.ic_darth_vader),
        Item("Forrest Gump", itemImage = R.drawable.ic_darth_vader),
        Item("Star Wars", itemImage = R.drawable.ic_darth_vader),
        Item(
            " The Lord of the Rings: The Return of the King",
            itemImage = R.drawable.ic_darth_vader
        ),
        Item("Jurassic Park", itemImage = R.drawable.ic_darth_vader),
        Item("The Deer Hunter", itemImage = R.drawable.ic_darth_vader),
        Item("Bonnie and Clyde", itemImage = R.drawable.ic_darth_vader),
        Item("The Silence of the Lambs", itemImage = R.drawable.ic_darth_vader),
        Item("E.T. the Extra-Terrestrial", itemImage = R.drawable.ic_darth_vader)
    )
}