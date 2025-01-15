package com.mf.homeassignment4.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mf.homeassignment4.data.models.SearchResultModel

@Composable
fun Grid(gridData: List<SearchResultModel>, paddingValues: PaddingValues, onItemClick: (searchResultItem: SearchResultModel)-> Unit) {
    val lazyGridState = rememberLazyGridState()
    LazyVerticalGrid(
        state = lazyGridState,
        modifier = Modifier.padding(paddingValues),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        itemsIndexed(gridData, key = {index, item -> item.link ?: "$index"}){ index: Int, item : SearchResultModel->
                AsyncImage(modifier = Modifier.height(100.dp).width(100.dp).clickable{ onItemClick(item)},model =item.media.m , contentDescription = item.description)
        }
    }
}