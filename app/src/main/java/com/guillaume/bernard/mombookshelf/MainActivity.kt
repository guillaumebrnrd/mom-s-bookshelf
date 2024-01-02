package com.guillaume.bernard.mombookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.ui.theme.*
import com.guillaume.bernard.mombookshelf.ui.theme.MomBookshelfTheme
import com.guillaume.bernard.mombookshelf.ui.theme.TextPrimary
import com.guillaume.bernard.mombookshelf.ui.theme.libreCaslonTextFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MomBookshelfTheme {
                MainView()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(Modifier.padding(16.dp)) {
                Toolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            var searchText by remember { mutableStateOf("") }
            TextField(
                value = searchText,
                placeholder = { Text(text = "Titre, auteur...", color = TextLight) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = null,
                        tint = TextLight
                    )
                },
                onValueChange = { searchText = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = SearchTextBackground,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Derniers ajouts",
                    fontFamily = libreCaslonTextFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "voir plus",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    color = ButtonNeutral,
                    fontSize = 13.sp,
                )
            }
            Bookshelf(SampleData.books)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Genres",
                    fontFamily = libreCaslonTextFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "voir plus",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    color = ButtonNeutral,
                    fontSize = 13.sp,
                )
            }
            LazyRow(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                items(SampleData.genres) {
                    Card(
                        Modifier
                            .size(180.dp, 100.dp)
                            .padding(end = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = genreDefaultColors.random()
                        )
                    ) {
                        Text(
                            text = it,
                            modifier = Modifier.padding(8.dp),
                            fontFamily = libreCaslonTextFamily,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Toolbar(modifier: Modifier = Modifier) {
    Text(
        text = "Mom's Bookshelf",
        modifier = modifier,
        fontFamily = libreCaslonTextFamily,
        fontWeight = FontWeight.Bold,
        color = TextPrimary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun Bookshelf(books: Array<Book>) {
    Column {
        LazyRow(
            modifier = Modifier
        ) {
            item { Spacer(modifier = Modifier.padding(start = 60.dp)) }
            items(books) {
                BookView(it)
            }
        }
        Shelf()
    }
}

@Composable
fun BookView(book: Book) {
    Box(modifier = Modifier.padding(end = 10.dp)) {
        Box(
            modifier = Modifier
                .size(height = 100.dp, width = 65.dp)
                .offset(4.dp, (-2).dp)
                .clip(RoundedCornerShape(10.dp))
                .background(BookPages)
        )
        Box(
            modifier = Modifier
                .size(height = 100.dp, width = 65.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(bookDefaultCovers.random())
                .padding(8.dp)
        ) {
            Text(
                book.title,
                fontSize = 10.sp,
                color = Color.White,
                lineHeight = 10.sp
            )
        }
    }
}

@Composable
fun Shelf() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-30).dp)
            .zIndex(-10f)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .offset(x = 30.dp)
                    .size(height = 50.dp, width = 60.dp)
                    .clip(TriangleShape)
                    .background(ShelfShadow)

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(height = 50.dp, width = 0.dp)
                    .clip(RectangleShape)
                    .background(ShelfShadow)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 30.dp)
                .size(height = 16.dp, width = 0.dp)
                .clip(RectangleShape)
                .background(ShelfBorder)
        )
    }
}

private val TriangleShape = GenericShape { size, _ ->
    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
}

@Preview(
    showBackground = true,
    device = "spec:width=1080px,height=1920px"
)
@Composable
fun MainViewPreview() {
    MomBookshelfTheme {
        MainView()
    }
}