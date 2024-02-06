package com.guillaume.bernard.mombookshelf.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guillaume.bernard.mombookshelf.AppViewModelProvider
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.model.BookViewModel
import com.guillaume.bernard.mombookshelf.model.NewBookViewModel
import com.guillaume.bernard.mombookshelf.ui.components.BookView

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit,
    onSaveButtonClicked: (NewBookViewModel) -> Unit,
    viewModel: NewBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val uiState = viewModel.bookState

        BookForm(uiState = uiState, viewModel = viewModel)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FilledTonalButton(
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(id = R.string.back_button))
            }
            Button(
                onClick = { onSaveButtonClicked(viewModel) },
                enabled = viewModel.isInputValid()
            ) {
                Text(stringResource(id = R.string.save_button))
            }

        }
    }
}

@Composable
fun BookForm(uiState: Book, viewModel: BookViewModel) {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            BookView(Modifier, uiState) {}
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.title,
            onValueChange = viewModel::setTitle,
            label = { Text(stringResource(id = R.string.add_book_title)) },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.author,
            onValueChange = viewModel::setAuthor,
            label = { Text(stringResource(id = R.string.add_book_author)) },
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = if (uiState.published == 0) "" else uiState.published.toString(),
            onValueChange = { viewModel.setPublished(published = it.toIntOrNull() ?: 0) },
            label = { Text(stringResource(id = R.string.add_book_published)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            minLines = 4,
            value = uiState.description,
            onValueChange = viewModel::setDescription,
            label = { Text(stringResource(id = R.string.add_book_description)) },
        )
    }
}

@Composable
@Preview
fun AddBookScreenPreview() {
    AddBookScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        onCancelButtonClicked = {},
        onSaveButtonClicked = {}
    )
}