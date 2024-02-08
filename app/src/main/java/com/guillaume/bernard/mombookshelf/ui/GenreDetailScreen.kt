package com.guillaume.bernard.mombookshelf.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guillaume.bernard.mombookshelf.AppViewModelProvider
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.model.BooksGenreViewModel
import com.guillaume.bernard.mombookshelf.ui.components.BookDetailed

@Composable
fun GenreDetailScreen(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit,
    onBackButtonClicked: () -> Unit,
    viewModel: BooksGenreViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = viewModel.id)
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
        FilledTonalButton(
            onClick = onBackButtonClicked
        ) {
            Text(stringResource(id = R.string.back_button))
        }
    }
}

@Composable
@Preview
fun GenreDetailScreenPreview() {
    GenreDetailScreen(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        onBookClicked = {},
        onBackButtonClicked = {}
    )
}
