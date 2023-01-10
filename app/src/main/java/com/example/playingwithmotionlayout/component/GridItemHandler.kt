package com.example.playingwithmotionlayout.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.playingwithmotionlayout.model.Item
import com.example.playingwithmotionlayout.model.ListPreviewParameterProvider
import com.example.playingwithmotionlayout.ui.theme.PlayingWithMotionLayoutTheme

@Preview(showBackground = true)
@Composable
fun GridItemHandlerPreview(
    @PreviewParameter(ListPreviewParameterProvider::class) list: List<Item>
) {
    PlayingWithMotionLayoutTheme {
        GridItemHandler(
            list = list,
            columns = 2,
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**-------------------------------------------------------------------------------------- *
 *                                  W  A  R  N  I  N  G                                   *
 * -------------------------------------------------------------------------------------- *
 * This composable function is only for illustration purpose.                             *
 * DO NOT attempt using a Column component whose content is built dynamically.            *
 * This is highly inefficient and you should prefer using a LazyColumn component instead. *
 * -------------------------------------------------------------------------------------- */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridItemHandler(
    list: List<Item>,
    columns: Int,
    modifier: Modifier = Modifier,
//    scrollState: ScrollState = rememberScrollState(),
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    val chunkedList = remember(list, columns) {
        list.chunked(columns)
    }

    //....
    LazyVerticalGrid(
        cells = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(chunkedList.size) { item ->
            list.forEach { list ->
                GridItemCard(
                    item = list,
                    modifier = Modifier
                        .padding(2.dp)
//                        .weight(1f)
                )
            }
            /*GridItemCard(
                item = item,
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
            )*/
        }
    }

    //....


/*    Column(
        modifier = modifier.zIndex(-1f),
        ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(contentPadding.calculateTopPadding())
        )

        chunkedList.forEach { chunk ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .width(contentPadding.calculateStartPadding(LocalLayoutDirection.current))
                )

                chunk.forEach { list ->
                    GridItemCard(
                        item = list,
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1f)
                    )
                }

                val emptyCells = columns - chunk.size
                if (emptyCells > 0) {
                    Spacer(modifier = Modifier.weight(emptyCells.toFloat()))
                }

                Spacer(
                    modifier = Modifier
                        .height(2.dp)
                        .width(contentPadding.calculateEndPadding(LocalLayoutDirection.current))
                )
            }
        }

        Spacer(
            modifier = Modifier
                .height(2.dp)
                .height(contentPadding.calculateBottomPadding())
        )
    }*/
}
