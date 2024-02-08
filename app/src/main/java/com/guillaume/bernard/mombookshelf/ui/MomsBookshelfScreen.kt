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
    @StringRes var title: Int,
    val routeWithArg: String,
    val route: String,
    val arg: String = "",
    val canNavigateBack: Boolean = true
) {
    Home(title = R.string.app_name, "home", "home", canNavigateBack = false),
    Collection(
        title = R.string.title_collection,
        "collection",
        "collection",
        canNavigateBack = false
    ),
    Profile(title = R.string.title_profile, "profile", "profile", canNavigateBack = false),
    NewBook(title = R.string.title_newbook, "new_book", "new_book"),
    EditBook(title = R.string.title_editbook, "edit/{bookId}", "edit/", "bookId"),
    BookDetail(title = R.string.title_book_detail, "book/{bookId}", "book/", "bookId"),
    Genres(title = R.string.title_genre, "genres", "genres"),
    GenreDetail(title = R.string.title_genre, "genre/{genre}", "genre/", "genre")
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
                            navController.navigate(MomsBookshelfScreen.Collection.route) {
                                popUpTo(0)
                            }
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
                            navController.navigate(MomsBookshelfScreen.Profile.route) {
                                popUpTo(0)
                            }
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
            startDestination = MomsBookshelfScreen.Home.routeWithArg,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Home screen
            composable(route = MomsBookshelfScreen.Home.routeWithArg) {
                HomeScreen(modifier = Modifier.fillMaxSize(),
                    onBookClicked = { book -> navController.navigate(MomsBookshelfScreen.BookDetail.route + book.id) },
                    onMoreBookTextClicked = { navController.navigate(MomsBookshelfScreen.Collection.route) },
                    onMoreGenreTextClicked = { navController.navigate(MomsBookshelfScreen.Genres.route) })
            }
            // Collection screen
            composable(route = MomsBookshelfScreen.Collection.routeWithArg) {
                CollectionScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onBookClicked = { book -> navController.navigate(MomsBookshelfScreen.BookDetail.route + book.id) },
                )
            }
            // Profile screen
            composable(route = MomsBookshelfScreen.Profile.routeWithArg) {
                // TODO profile screen
            }
            // Add a book screen
            composable(route = MomsBookshelfScreen.NewBook.routeWithArg) {
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
                route = MomsBookshelfScreen.EditBook.routeWithArg,
                arguments = listOf(navArgument(MomsBookshelfScreen.EditBook.arg) {
                    type = NavType.LongType
                })
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
                    }
                )
            }
            // Book detail screen
            composable(
                route = MomsBookshelfScreen.BookDetail.routeWithArg,
                arguments = listOf(navArgument(MomsBookshelfScreen.BookDetail.arg) {
                    type = NavType.LongType
                })
            ) {
                BookDetailScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onGenreButtonClick = { navController.navigate(MomsBookshelfScreen.GenreDetail.route + it) },
                    onBackButtonClicked = { navController.navigateUp() },
                    onEditBookButtonClicked = { navController.navigate(MomsBookshelfScreen.EditBook.route + it.id) })
            }
            // Genres screen
            composable(route = MomsBookshelfScreen.Genres.routeWithArg) {
                // TODO List all genres
            }
            // Genre detail
            composable(
                route = MomsBookshelfScreen.GenreDetail.routeWithArg,
                arguments = listOf(
                    navArgument(MomsBookshelfScreen.GenreDetail.arg) { type = NavType.StringType })
            ) {
                GenreDetailScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onBookClicked = { navController.navigate(MomsBookshelfScreen.BookDetail.route + it.id) },
                    onBackButtonClicked = { navController.navigateUp() })
            }
        }
    }
}

@Composable
@Preview
fun MomsBookshelfPreview() {
    MomsBookshelfApp()
}