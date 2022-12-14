package fhnw.emoba.freezerapp.ui.screens

import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.data.Album

import fhnw.emoba.freezerapp.model.AvailableScreen
import fhnw.emoba.freezerapp.model.FreezerModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun search(model: FreezerModel) {
    with(model) {

            val keyboard = LocalSoftwareKeyboardController.current

        Column(Modifier.padding(10.dp)) {
            OutlinedTextField(value         = searchWord,
                onValueChange = { searchWord = it },
                singleLine    = true,
                trailingIcon  = {
                    IconButton(onClick = {
                        keyboard?.hide()
                        searchWord = ""
                        launchSearch()})
                    {
                        Icon(Icons.Filled.Clear, "löschen")
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { keyboard?.hide()
                    launchSearch() }),
                colors          = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = MaterialTheme.colors.secondary),
                placeholder     = { Text("Suche") },
                modifier        = Modifier
                    .fillMaxWidth()
                    // das ist eigentlich nur fuer den Emulator interessant. Auf einem Smartphone wird kaum ein "Enter" eingegeben
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
                            searchWord = searchWord.replace("\n", "", ignoreCase = true)
                            launchSearch()
                        }
                        false
                    })
            Switches(model)
            ShowResults(model)
        }
    }
}



@Composable
fun Switches(model: FreezerModel) {
    with(model) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {

            Text("Track")
            Switch(checked = rememberTrack, onCheckedChange = { toggleTrack() })

            Text("Album")
            Switch(checked = rememberAlbum, onCheckedChange = { toggleAlbum() })

        }
    }
}

@Composable
fun ShowResults(model: FreezerModel) {
    val state = rememberLazyListState()
    with(model) {
        if (rememberTrack){
            LazyColumn(state = state, modifier = Modifier.fillMaxWidth()) {
                items(resultTrackList) {
                    TrackView(it, model)
                }
        }
        }
        if (rememberAlbum){
            LazyColumn(state = state, modifier = Modifier.fillMaxWidth()) {
                items(resultAlbumList) {
                    AlbumView(it, model)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlbumView(it: Album, model: FreezerModel) {
    with(model) {
        Card(shape = RoundedCornerShape(20.dp), elevation = 10.dp, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            onClick = {
               loadTracks(it)
                currentScreen = AvailableScreen.TRACK
            }) {
            Column(modifier = Modifier.fillMaxWidth()) {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = it.title, modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically), fontSize = 14.sp)
                    Spacer(Modifier.width(20.dp))

                }
                Text(text = it.artist.name, modifier = Modifier
                    .padding(10.dp)
                    .align(
                        Alignment.Start
                    ), fontSize = 13.sp, color = Color.DarkGray)
            }
        }
    }
}

