package com.example.playingwithmotionlayout.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.playingwithmotionlayout.component.*
import com.example.playingwithmotionlayout.component.appBar.MotionAppBar
import com.example.playingwithmotionlayout.model.populateList

@Composable
fun MainScreenContent() {
    val lazyScrollState = rememberLazyListState()
    Scaffold(//
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MotionAppBar(lazyScrollState)
        }
    ) {
        GridDataContent(lazyScrollState)
    }
}

/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridDataContent(lazyScrollState: LazyListState) {
    val maxToolbarHeight = 176.dp

    LazyColumn(//
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)    // bg color of the Grid content
            .animateContentSize(),
        state = lazyScrollState
    ) {
        itemsIndexed(populateList()) { index, item ->
            //3. List item
//          RepoItem(repo = item)

            GridItemHandler(//
                list = populateList(),
                columns = 2,
                modifier = Modifier
                    .layoutId("data_content")
                    .zIndex(1f),
//                  scrollState = scrollState,
//                contentPadding = PaddingValues(top = maxToolbarHeight)  // To provide padding above grid content
            )
          if (index != populateList().lastIndex)
              Divider(modifier = Modifier.padding(8.dp))
        }
    }
}*/

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridDataContent(lazyScrollState: LazyListState) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        content = {
            items(populateList()) { itemList ->

                GridItemCard(
                    item = itemList,
                    modifier = Modifier
                        .padding(2.dp)
                )
            }
        },
        state = lazyScrollState
    )
}