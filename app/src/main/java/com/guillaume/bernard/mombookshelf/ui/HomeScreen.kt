package com.guillaume.bernard.mombookshelf.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guillaume.bernard.mombookshelf.AppViewModelProvider
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.SampleData
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.model.LastBookViewModel
import com.guillaume.bernard.mombookshelf.ui.components.BookView
import com.guillaume.bernard.mombookshelf.ui.theme.libreCaslonTextFamily
import com.guillaume.bernard.mombookshelf.ui.theme.ralewayFamily

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit,
    onMoreBookTextClicked: () -> Unit,
    onMoreGenreTextClicked: () -> Unit,
    viewModel: LastBookViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.last_book),
                fontFamily = libreCaslonTextFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(id = R.string.see_more),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onMoreBookTextClicked() },
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
            )
        }

        Card(
            modifier = Modifier.padding(horizontal = 24.dp),
            shape = MaterialTheme.shapes.medium
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                BookView(book = uiState.value, onClick = onBookClicked)
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = uiState.value.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
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
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Genres",
                fontFamily = libreCaslonTextFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = stringResource(id = R.string.see_more),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onMoreGenreTextClicked() },
                fontFamily = ralewayFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
            )
        }
        LazyRow(
            modifier = Modifier.padding(start = 24.dp)
        ) {
            items(SampleData.genres) {
                Card(
                    Modifier
                        .size(180.dp, 100.dp)
                        .padding(end = 8.dp),
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.padding(8.dp),
                        fontFamily = libreCaslonTextFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

    }

}