package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.Track
import fhnw.emoba.freezerapp.model.AvailableScreen
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun showTracks(model: FreezerModel){
    val state = rememberLazyListState()

    with(model){
        LazyColumn(state = state, modifier = Modifier.fillMaxWidth()){
            items(trackRadioList) {
                TrackView(it, model)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackView(it: Track, model: FreezerModel) {
    with(model){
        Card(shape = RoundedCornerShape(20.dp),elevation = 10.dp, modifier = Modifier.padding(10.dp).fillMaxWidth(),
        onClick = {setTrack(it.preview, it.title, it.album)
        currentScreen = AvailableScreen.PLAYER}) {
            Text(text = it.title, modifier = Modifier.padding(10.dp))
        }
    }
}
