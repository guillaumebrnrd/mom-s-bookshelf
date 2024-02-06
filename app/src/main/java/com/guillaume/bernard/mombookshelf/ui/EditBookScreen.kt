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
import com.guillaume.bernard.mombookshelf.model.EditBookViewModel
import com.guillaume.bernard.mombookshelf.model.NewBookViewModel
import com.guillaume.bernard.mombookshelf.ui.components.BookView

@Composable
fun EditBookScreen(
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit,
    onSaveButtonClicked: (EditBookViewModel) -> Unit,
    viewModel: EditBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
@Preview
fun EditBookScreenPreview() {
    EditBookScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        onCancelButtonClicked = {},
        onSaveButtonClicked = {}
    )
}