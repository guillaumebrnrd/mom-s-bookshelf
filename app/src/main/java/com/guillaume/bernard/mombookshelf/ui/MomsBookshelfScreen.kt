package com.guillaume.bernard.mombookshelf.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.guillaume.bernard.mombookshelf.R
import com.guillaume.bernard.mombookshelf.ui.theme.libreCaslonTextFamily
import kotlinx.coroutines.launch

enum class MomsBookshelfScreen(
    @StringRes var title: Int, val route: String, val canNavigateBack: Boolean = true
) {
    Home(title = R.string.app_name, "home", false),
    Collection(title = R.string.title_collection, "collection", false),
    Profile(title = R.string.title_profile, "profile", false),
    NewBook(title = R.string.title_newbook, "new_book"),
    EditBook(title = R.string.title_editbook, "edit/{bookId}"),
    BookDetail(title = R.string.title_book_detail, "book/{bookId}")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MomsBookshelfAppBar(
    modifier: Modifier = Modifier,
    currentScreen: MomsBookshelfScreen,
    navigateUp: () -> Unit,
) {
    TopAppBar(title = {
        Text(
            stringResource(currentScreen.title),
            fontFamily = libreCaslonTextFamily,
            fontWeight = FontWeight.Bold
        )
    }, modifier = modifier, navigationIcon = {
        if (currentScreen.canNavigateBack) {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        }
    })
}

@Composable
fun MomsBookshelfApp(navController: NavHostController = rememberNavController()) {
    val coroutineScope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MomsBookshelfScreen.values().find {
        it.route == backStackEntry?.destination?.route
    } ?: MomsBookshelfScreen.Home

    Scaffold(topBar = {
        MomsBookshelfAppBar(currentScreen = currentScreen, navigateUp = {
            navController.navigateUp()
        })
    },
        // Main navigation
        bottomBar = {
            NavigationBar {
                NavigationBarItem(selected = currentScreen == MomsBookshelfScreen.Home, onClick = {
                    if (currentScreen != MomsBookshelfScreen.Home) {
                        navController.navigate(MomsBookshelfScreen.Home.route) { popUpTo(0) }
                    }
                }, icon = {
                    Icon(
                        Icons.Rounded.Home,
                        contentDescription = stringResource(id = R.string.nav_home)
                    )
                }, label = { Text(stringResource(id = R.string.nav_home)) })

                NavigationBarItem(selected = currentScreen == MomsBookshelfScreen.Collection,
                    onClick = {
                        if (currentScreen != MomsBookshelfScreen.Collection) {
                            navController.navigate(MomsBookshelfScreen.Collection.route) { popUpTo(0) }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_book),
                            contentDescription = stringResource(id = R.string.nav_collection)
                        )
                    },
                    label = { Text(stringResource(id = R.string.nav_collection)) })

                NavigationBarItem(selected = currentScreen == MomsBookshelfScreen.Profile,
                    onClick = {
                        if (currentScreen != MomsBookshelfScreen.Profile) {
                            navController.navigate(MomsBookshelfScreen.Profile.route) { popUpTo(0) }
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Rounded.AccountCircle,
                            contentDescription = stringResource(id = R.string.nav_profile)
                        )
                    },
                    label = { Text(text = stringResource(id = R.string.nav_profile)) })
            }
        },

        // Add a book button
        floatingActionButton = {
            if (!currentScreen.canNavigateBack) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(MomsBookshelfScreen.NewBook.route)
                    },
                ) {
                    Icon(Icons.Filled.Add, stringResource(id = R.string.add_book_button))
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MomsBookshelfScreen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Home screen
            composable(route = MomsBookshelfScreen.Home.route) {
                HomeScreen(modifier = Modifier.fillMaxSize(),
                    onBookClicked = { book -> navController.navigate("book/${book.id}") },
                    onMoreBookTextClicked = { navController.navigate(MomsBookshelfScreen.Collection.route) },
                    onMoreGenreTextClicked = { /* TODO on genre's "see more" text clicked */ })
            }
            // Collection screen
            composable(route = MomsBookshelfScreen.Collection.route) {
                CollectionScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onBookClicked = { book -> navController.navigate("book/${book.id}") },
                )
            }
            // Profile screen
            composable(route = MomsBookshelfScreen.Profile.route) {
                // TODO profile screen
            }
            // Add a book screen
            composable(route = MomsBookshelfScreen.NewBook.route) {
                AddBookScreen(
                    onCancelButtonClicked = { navController.navigateUp() },
                    onSaveButtonClicked = {
                        coroutineScope.launch {
                            it.saveBook()
                            navController.navigateUp()
                        }
                    }
                )
            }
            // Edit a book screen
            composable(
                route = MomsBookshelfScreen.EditBook.route,
                arguments = listOf(navArgument("bookId") { type = NavType.LongType })
            ) {
                EditBookScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onCancelButtonClicked = {
                        navController.navigateUp()
                    },
                    onSaveButtonClicked = {
                        coroutineScope.launch {
                            it.updateBook()
                            navController.navigateUp()
                        }
                    })
            }
            // Book detail screen
            composable(
                route = MomsBookshelfScreen.BookDetail.route,
                arguments = listOf(navArgument("bookId") { type = NavType.LongType })
            ) {
                BookDetailScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onBackButtonClicked = { navController.navigateUp() },
                    onEditBookButtonClicked = { navController.navigate("edit/${it.id}") })
            }
        }
    }
}

@Composable
@Preview
fun MomsBookshelfPreview() {
    MomsBookshelfApp()
}