package com.assignment.pokemoncatcher.presentation.views.components.paging

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.BuildConfig
import com.assignment.pokemoncatcher.core.utils.DebugLog
import com.assignment.pokemoncatcher.presentation.views.components.ContentLoading

@Composable
fun LazyGridPaging(
    modifier: Modifier = Modifier,
    key: (idx: Int) -> Any,
    onLoadNext: () -> Unit,
    gridCells: GridCells = GridCells.Fixed(
        3
    ),
    isLoading: Boolean = false,
    itemCount: Int,
    itemContent: @Composable LazyGridItemScope.(index: Int) -> Unit
) {
    val lazyGridState =
        rememberLazyGridState()
    val pagingState =
        rememberGridPagingScrollState(
            lazyGridState = lazyGridState
        )

    LaunchedEffect(pagingState.reachBottom) {
        if (!isLoading) {
            DebugLog.log(msg = "Reach bottom grid...")
            onLoadNext()
        }
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = gridCells,
        state = lazyGridState,
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp
        )
    ) {
        items(
            count = itemCount,
            key = { key(it) }) {
            itemContent(it)
        }

        if (isLoading) {
            item {
                ContentLoading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }
        }
    }
}

@Composable
fun rememberGridPagingScrollState(
    lazyGridState: LazyGridState
): PagingScrollState {
    val pagingScrollState by remember {
        derivedStateOf {
            val layoutInfo =
                lazyGridState.layoutInfo
            val reachBottom = when {
                layoutInfo.visibleItemsInfo.isEmpty() -> false
                else -> {
                    val lastVisibleItem =
                        layoutInfo.visibleItemsInfo.last()
                    val viewportHeight =
                        layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

                    (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount &&
                            lastVisibleItem.offset.y + lastVisibleItem.size.height >= viewportHeight)
                }
            }
            PagingScrollState(
                reachBottom = reachBottom
            )
        }
    }

    return pagingScrollState
}

data class PagingScrollState(
    val reachBottom: Boolean = false,
)