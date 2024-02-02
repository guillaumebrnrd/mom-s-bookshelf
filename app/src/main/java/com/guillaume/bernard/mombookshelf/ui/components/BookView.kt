package com.guillaume.bernard.mombookshelf.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.model.BookState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookView(modifier: Modifier = Modifier, book: BookState, onClick: (BookState) -> Unit) {
    Box(
        modifier = modifier
            .clickable { onClick(book) }
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
