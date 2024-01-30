package com.guillaume.bernard.mombookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
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
    Scaffold(
        topBar = {
            Row(Modifier.padding(24.dp)) {
                Toolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.AccountBox,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }

            )
        },
        containerColor = Background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
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
                    .padding(horizontal = 24.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
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

            Card(
                modifier = Modifier.padding(24.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center) {
                        BookView(book = SampleData.books.first())
                    }
                    Text(
                        text = "Card Title",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = "This is some sample text for the card.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
//            Bookshelf(SampleData.books)
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
                modifier = Modifier.padding(start = 24.dp)
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
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookView(book: Book) {
    Box(modifier = Modifier.padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(height = 180.dp, width = 130.dp)
                .offset(4.dp, (-2).dp)
                .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                .background(BookPages)
        )

        Box(
            modifier = Modifier
                .size(height = 180.dp, width = 130.dp)
                .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                .background(bookDefaultCovers.random())
        ) {
            GlideImage(
                model = "https://cdn1.booknode.com/book_cover/72/full/1984-72084.jpg",
                loading = placeholder(R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
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