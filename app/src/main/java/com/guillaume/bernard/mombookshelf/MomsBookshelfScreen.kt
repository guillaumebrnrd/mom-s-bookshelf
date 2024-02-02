package com.guillaume.bernard.mombookshelf

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guillaume.bernard.mombookshelf.ui.CollectionScreen
import com.guillaume.bernard.mombookshelf.ui.HomeScreen
import com.guillaume.bernard.mombookshelf.ui.theme.libreCaslonTextFamily

enum class MomsBookshelfScreen(@StringRes val title: Int, val canNavigateBack: Boolean = true) {
    Home(title = R.string.app_name, false),
    Collection(title = R.string.title_collection, false),
    Profile(title = R.string.title_profile, false),
    NewBook(title = R.string.title_newbook),
    BookDetail(title = R.string.none), // Dynamic title based on book's title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MomsBookshelfAppBar(
    modifier: Modifier = Modifier,
    currentScreen: MomsBookshelfScreen,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                stringResource(currentScreen.title),
                fontFamily = libreCaslonTextFamily,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (currentScreen.canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MomsBookshelfApp(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MomsBookshelfScreen.valueOf(
        backStackEntry?.destination?.route ?: MomsBookshelfScreen.Home.name
    )
    Scaffold(
        topBar = {
            MomsBookshelfAppBar(currentScreen = currentScreen, navigateUp = {
                navController.navigateUp()
            })
        },

        // Main navigation
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = currentScreen == MomsBookshelfScreen.Home,
                    onClick = {
                        if (currentScreen != MomsBookshelfScreen.Home) {
                            navController.navigate(MomsBookshelfScreen.Home.name)
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Rounded.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = currentScreen == MomsBookshelfScreen.Collection,
                    onClick = {
                        if (currentScreen != MomsBookshelfScreen.Collection) {
                            navController.navigate(MomsBookshelfScreen.Collection.name)
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_book),
                            contentDescription = "Collection"
                        )
                    },
                    label = { Text("Collection") })
                NavigationBarItem(
                    selected = currentScreen == MomsBookshelfScreen.Profile,
                    onClick = {
                        if (currentScreen != MomsBookshelfScreen.Profile) {
                            navController.navigate(MomsBookshelfScreen.Profile.name)
                        }
                    },
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

        // Add a book button
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(MomsBookshelfScreen.NewBook.name)
                },
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MomsBookshelfScreen.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Home screen
            composable(route = MomsBookshelfScreen.Home.name) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize(),
                    onBookClicked = { book ->  // TODO pass [book] as parameter
                        navController.navigate(MomsBookshelfScreen.BookDetail.name)
                    },
                    onMoreBookTextClicked = { /* TODO on book's "see more" text clicked */ },
                    onMoreGenreTextClicked = { /* TODO on genre's "see more" text clicked */ }
                )
            }
            // Collection screen
            composable(route = MomsBookshelfScreen.Collection.name) {
                CollectionScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onBookClicked = { book ->  // TODO pass [book] as parameter
                        navController.navigate(MomsBookshelfScreen.BookDetail.name)
                    },
                )
            }
            // Profile screen
            composable(route = MomsBookshelfScreen.Profile.name) {
                // TODO profile screen
            }
        }
    }
}

@Composable
@Preview
fun MomsBookshelfPreview() {
    MomsBookshelfApp()
}