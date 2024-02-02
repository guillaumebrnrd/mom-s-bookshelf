package com.guillaume.bernard.mombookshelf

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

enum class MomsBookshelfScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Collection(title = R.string.title_collection),
    Settings(title = R.string.title_profile),
}

@Composable
fun MomsBookshelfAppBar(
    currentScreen: MomsBookshelfScreen,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
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

    )
}

@Composable
fun MomsBookshelfApp(navController: NavHostController = rememberNavController()) {

}

@Composable
@Preview
fun MomsBookshelfPreview() {
    MomsBookshelfApp()
}