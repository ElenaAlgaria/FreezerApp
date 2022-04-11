package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.data.Track
//import fhnw.emoba.freezerapp.data.TracksNoAlbum
import fhnw.emoba.freezerapp.model.AvailableScreen
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun showTracks(tracks: List<Track>, model: FreezerModel) {
    val state = rememberLazyListState()

    with(model) {
        LazyColumn(state = state, modifier = Modifier.fillMaxWidth()) {
            items(tracks) {
                TrackView(it, model)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackView(it: Track, model: FreezerModel) {
    with(model) {
        Card(shape = RoundedCornerShape(20.dp), elevation = 10.dp, modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
            onClick = {
                setTrack(trackList.indexOf(it), it, it.album)
                currentScreen = AvailableScreen.PLAYER
            }) {
            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.title, modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.CenterVertically), fontSize = 14.sp
                    )
                    Spacer(Modifier.width(20.dp))

                    IconButton(onClick = { switchFavorite(it) }) {
                        Icon(
                            imageVector = if (tracksFavorites.contains(it).not()
                            ) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                            contentDescription = "favBorder"
                        )
                }
            }

            Text(
                text = it.artist.name, modifier = Modifier.padding(10.dp).align(
                    Alignment.Start
                ), fontSize = 13.sp, color = Color.DarkGray
            )
        }
    }
}
}
