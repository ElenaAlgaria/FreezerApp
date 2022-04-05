package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.model.FreezerModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.ui.text.font.FontWeight
import fhnw.emoba.freezerapp.model.AvailableScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupRadio(model: FreezerModel) {
    val state = rememberLazyListState()

    with(model) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 0.dp, 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Radios", modifier = Modifier.align(Alignment.TopStart), fontWeight = FontWeight.Bold)
            LazyRow(state = state, modifier = Modifier
                .fillMaxSize()
                .padding(2.dp, 35.dp, 2.dp, 0.dp)) {
                items(radioList) {
                    RadioView(it, model)
                }
            }
            Text(text = "Alben", modifier = Modifier.align(Alignment.CenterStart), fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RadioView(radio: Radio, model: FreezerModel){
    with(model) {
            Card(modifier = Modifier.padding(5.dp), onClick = {loadRadioTracks(radio.tracks)
            currentScreen = AvailableScreen.TRACK}) {
            Column() {
                    Image(
                        bitmap = radio.loadedImage!!, contentDescription = "img",
                        modifier = Modifier
                            .shadow(4.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    Text(text = radio.title, modifier = Modifier.align(Alignment.CenterHorizontally))

                }
        }
    }

}


