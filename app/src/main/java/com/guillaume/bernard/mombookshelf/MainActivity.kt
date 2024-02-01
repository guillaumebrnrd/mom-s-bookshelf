package com.guillaume.bernard.mombookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.guillaume.bernard.mombookshelf.model.Book
import com.guillaume.bernard.mombookshelf.ui.theme.AppTheme
import com.guillaume.bernard.mombookshelf.ui.theme.libreCaslonTextFamily
import com.guillaume.bernard.mombookshelf.ui.theme.ralewayFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
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

            NavigationBar {
                NavigationBarItem(selected = true, onClick = {},
                    icon = {
                        Icon(
                            Icons.Rounded.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )
                NavigationBarItem(selected = false, onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_book),
                            contentDescription = "Collection"
                        )
                    },
                    label = { Text("Collection") })
                NavigationBarItem(selected = false, onClick = {},
                    icon = {
                        Icon(
                            Icons.Rounded.AccountCircle,
                            contentDescription = "Profile"
                        )
                    },
                    label = { Text(text = "Profile") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            var searchText by remember { mutableStateOf("") }
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
                colors = TextFieldDefaults.textFieldColors(
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
                    text = "Dernier ajout",
                    fontFamily = libreCaslonTextFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "voir plus",
                    modifier = Modifier.align(Alignment.CenterVertically),
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
                    BookView(book = SampleData.books.first())
                    Column(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Book Title",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Text(
                            text = "Jean Neige",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "2006",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            modifier = Modifier.padding(top = 6.dp),
                            text = "Lorem ipsum dolor sit amet, lorem ipsum dolor sit amet. Lormz dhiuoz zeir nknkigh zaih gi zehugig ihhharhi.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
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
                )
                Text(
                    text = "voir plus",
                    modifier = Modifier.align(Alignment.CenterVertically),
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

}

@Composable
fun Toolbar(modifier: Modifier = Modifier) {
    Text(
        text = "Mom's Bookshelf",
        modifier = modifier,
        fontFamily = libreCaslonTextFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BookView(book: Book) {
    Box(
        modifier = Modifier.padding(end = 10.dp)
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

@Preview(
    showBackground = true,
    device = "spec:width=1080px,height=1920px"
)
@Composable
fun MainViewPreview() {
    AppTheme {
        MainView()
    }
}