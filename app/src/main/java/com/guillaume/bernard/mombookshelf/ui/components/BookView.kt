package com.guillaume.bernard.mombookshelf.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.SampleData
import com.guillaume.bernard.mombookshelf.model.BookState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookView(modifier: Modifier = Modifier, book: BookState, onClick: (BookState) -> Unit) {
    Box(
        modifier = modifier
            .size(134.dp, 182.dp)
            .shadow(15.dp, RoundedCornerShape(10.dp))
            .clickable { onClick(book) },
        contentAlignment = Alignment.BottomStart
    ) {
        Box(
            modifier = Modifier
                .size(height = 180.dp, width = 130.dp)
                .offset(4.dp, (-2).dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        )

        Box(
            modifier = Modifier
                .size(height = 180.dp, width = 130.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            GlideImage(
                model = "https://cdn1.booknode.com/book_cover/72/full/1984-72084.jpg",
                loading = placeholder(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BookDetailed(modifier: Modifier = Modifier, book: BookState, onClick: (BookState) -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        BookView(
            book = book, onClick = onClick
        )
        Card(
            modifier = Modifier
                .width(180.dp)
                .padding(top = 150.dp)
                .offset(y = (-16).dp)
                .zIndex(-1f),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 52.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
                text = book.title,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                minLines = 2,
                maxLines = 2
            )
        }
    }
}

@Composable
@Preview
fun BookViewPreview() {
    BookView(book = SampleData.books.random(), onClick = {})
}

@Composable
@Preview
fun BookDetailedPreview() {
    BookDetailed(book = SampleData.books.random(), onClick = {})
}