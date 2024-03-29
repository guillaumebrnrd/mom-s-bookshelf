package com.guillaume.bernard.mombookshelf.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guillaume.bernard.mombookshelf.AppViewModelProvider
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.model.AllBooksViewModel
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.ui.components.BookDetailed

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit,
    viewModel: AllBooksViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.searchResults.collectAsState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        var searchText by remember { mutableStateOf("") }
        val containerColor = MaterialTheme.colorScheme.surfaceVariant
        TextField(
            value = searchText,
            placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                )
            },
            onValueChange = {
                searchText = it
                viewModel.applyFilter(it)
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 16.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(uiState.value) { book ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    BookDetailed(
                        modifier = Modifier.align(Alignment.Center),
                        book = book,
                        onClick = onBookClicked
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CollectionScreenPreview() {
    CollectionScreen(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        onBookClicked = {}
    )
}
