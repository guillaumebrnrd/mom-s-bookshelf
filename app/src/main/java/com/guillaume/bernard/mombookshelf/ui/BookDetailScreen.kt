package com.guillaume.bernard.mombookshelf.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.SampleData
import com.guillaume.bernard.mombookshelf.model.BookState
import com.guillaume.bernard.mombookshelf.ui.components.BookView

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    book: BookState,
    onBackButtonClicked: () -> Unit,
    onEditBookButtonClicked: (BookState) -> Unit
) {
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
            book = book,
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
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = book.author.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = book.published.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = book.description,
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
                onClick = { onEditBookButtonClicked(book) }) {
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
        book = SampleData.books.random(),
        onBackButtonClicked = {},
        onEditBookButtonClicked = {}
    )
}