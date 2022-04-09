package fhnw.emoba.freezerapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.model.AvailableScreen
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.screens.*


@Composable
fun AppUI(model: FreezerModel) {
    with(model) {
        MaterialTheme(
            colors = lightColors(
                primary = Color(142, 183, 171),
                surface = Color(0xFFF8F7F3),
                onPrimary = Color(0xFF343434),
                secondary = Color(0xFF347880)
            )
        ) {
            Scaffold(topBar = { TopBar(model) },
                content = { Body(model) },
                bottomBar = { BottomBar(model) })
        }

    }
}

@Composable
fun TopBar(model: FreezerModel) {
    with(model) {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.deezerlogo),
                    contentDescription = "logo", alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )
            },
            modifier = Modifier
                .background(color = Color(142, 183, 171))
                .fillMaxWidth()
        )

    }
}

@Composable
fun Body(model: FreezerModel) {
    with(model) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Crossfade(targetState = currentScreen) { tab ->
                when (tab) {
                    AvailableScreen.SEARCH -> {
                        search(model = model)
                    }
                    AvailableScreen.PLAYER -> {
                        setupPlayer(model = model)
                    }
                    AvailableScreen.EXPLORE -> {
                        SetupRadio(model = model)
                    }
                    AvailableScreen.TRACK ->  {
                        showTracks(model = model, tracks = trackList)
                        showAlbumTracks(tracks = trackAlbumList, model = model)
                    }

                    AvailableScreen.FAV -> {
                        showFavorites(model = model)
                    }
                }
            }
        }
    }
}



@Composable
fun BottomBar(model: FreezerModel) {
    with(model) {
        BottomNavigation {
            BottomNavigationItem(selected = AvailableScreen.values()[0] == currentScreen,
                icon = { Icon(Icons.Filled.MusicNote, contentDescription = null) },
                label = { Text(AvailableScreen.values()[0].title) },
                onClick = { currentScreen = AvailableScreen.values()[0] })

            BottomNavigationItem(selected = AvailableScreen.values()[1] == currentScreen,
                icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                label = { Text(AvailableScreen.values()[1].title) },
                onClick = { currentScreen = AvailableScreen.values()[1] })

            BottomNavigationItem(selected = AvailableScreen.values()[2] == currentScreen,
                icon = { Icon(Icons.Filled.PlayArrow, contentDescription = null )},
                label = { Text(AvailableScreen.values()[2].title)},
                onClick = { currentScreen = AvailableScreen.values()[2] })

            BottomNavigationItem(selected = AvailableScreen.values()[3] == currentScreen,
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null )},
                label = { Text(AvailableScreen.values()[3].title)},
                onClick = { currentScreen = AvailableScreen.values()[3] })
        }
    }
}

