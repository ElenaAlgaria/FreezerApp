package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun showFavorites(model: FreezerModel) {
    val state = rememberLazyListState()

    with(model) {
        LazyColumn(state = state, modifier = Modifier.fillMaxWidth()) {
            items(tracksFavorites) {
                TrackView(it, model)
            }
        }
    }
}
