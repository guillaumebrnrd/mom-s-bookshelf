package com.guillaume.bernard.mombookshelf.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guillaume.bernard.mombookshelf.AppViewModelProvider
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.model.DetailedBookViewModel
import com.guillaume.bernard.mombookshelf.ui.components.BookView

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
    onEditBookButtonClicked: (Book) -> Unit,
    viewModel: DetailedBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookView(
            modifier = Modifier
                .padding(bottom = 24.dp),
            book = uiState.value,
            onClick = { /* Already in the right screen */ })

        Card(
            shape = MaterialTheme.shapes.medium
        ) {

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = uiState.value.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = uiState.value.author,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = uiState.value.published.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = uiState.value.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(
                onClick = onBackButtonClicked
            ) {
                Text(stringResource(id = R.string.back_button))
            }
            Button(
                onClick = { onEditBookButtonClicked(uiState.value) }) {
                Text(stringResource(id = R.string.edit_button))
            }

        }
    }
}

@Composable
@Preview
fun BookDetailScreenPreview() {
    BookDetailScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        onBackButtonClicked = {},
        onEditBookButtonClicked = {}
    )
}