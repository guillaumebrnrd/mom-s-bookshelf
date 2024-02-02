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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guillaume.bernard.mombookshelf.SampleData
import com.guillaume.bernard.mombookshelf.model.BookState
import com.guillaume.bernard.mombookshelf.ui.components.BookView

@Composable
fun CollectionScreen(
    modifier: Modifier = Modifier,
    onBookClicked: (BookState) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        var searchText by remember { mutableStateOf("") }
        val containerColor = MaterialTheme.colorScheme.surfaceVariant
        TextField(
            value = searchText,
            placeholder = { Text(text = "Titre, auteur...") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = null,
                )
            },
            onValueChange = { searchText = it },
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
            items(SampleData.books) { book ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    BookView(
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
