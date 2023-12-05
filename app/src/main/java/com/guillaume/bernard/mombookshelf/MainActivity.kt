package com.guillaume.bernard.mombookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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

            Row {
                Toolbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Text(
                "Derniers ajouts",
                modifier = Modifier.padding(16.dp),
                fontFamily = libreCaslonTextFamily,
                color = TextPrimary
            )
            Bookshelf()
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
fun Bookshelf() {
    Column {
        LazyRow(
            modifier = Modifier
        ) {
            item { Spacer(modifier = Modifier.padding(start = 60.dp)) }
            items(15) {
                BookView()
            }
        }
        Shelf()
    }
}

@Composable
fun BookView() {
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
                .background(BookDefaultCover)
                .padding(8.dp)
        ) {
            Text(
                "A",
                fontSize = 50.sp,
                textAlign = TextAlign.End,
                style = Typography.titleLarge,
                color = Color.White
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
    device = "spec:width=480px,height=800px,dpi=240", showSystemUi = true
)
@Composable
fun GreetingPreview() {
    MomBookshelfTheme {
        MainView()
    }
}