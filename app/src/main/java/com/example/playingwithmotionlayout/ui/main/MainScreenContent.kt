package com.example.playingwithmotionlayout.ui.main

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.playingwithmotionlayout.component.GridItemHandler
import com.example.playingwithmotionlayout.component.appBar.MotionAppBar
import com.example.playingwithmotionlayout.component.repos
import com.example.playingwithmotionlayout.model.populateList
import com.example.playingwithmotionlayout.ui.theme.OffWhite

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
}