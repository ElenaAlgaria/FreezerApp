package fhnw.emoba.freezerapp.ui.screens

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
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.AvailableScreen

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun SetupRadio(model: FreezerModel) {
    with(model) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 16.dp, 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Radios", modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold, fontSize = 30.sp)
            LazyVerticalGrid(cells = GridCells.Adaptive(120.dp), contentPadding = PaddingValues(16.dp)) {
                items(radioList) {
                    RadioView(it, model)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RadioView(radio: Radio, model: FreezerModel){
    with(model) {
            Card(modifier = Modifier.padding(5.dp), onClick = {loadTracks(radio)
            currentScreen = AvailableScreen.TRACK}) {
            Column() {
                    Image(
                        bitmap = radio.loadedImage, contentDescription = "img",
                        modifier = Modifier
                            .shadow(4.dp).align(Alignment.CenterHorizontally),
                        contentScale = ContentScale.FillWidth
                    )

                    Text(text = radio.title, modifier = Modifier.align(Alignment.CenterHorizontally))

                }
        }
    }

}


