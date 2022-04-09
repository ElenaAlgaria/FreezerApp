package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun setupPlayer(model: FreezerModel) {
    with(model) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()) {

            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Text(
                    text = trackName, fontSize = 32.sp, modifier = Modifier

                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                )

                Text(
                    text = artist!!.name, fontSize = 24.sp, modifier = Modifier

                        .padding(16.dp, 16.dp, 16.dp, 0.dp)
                )

            }



            album?.loadedImage?.let {
                Image(
                    bitmap = it, contentDescription = "img",
                    modifier = Modifier
                        .shadow(4.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillWidth
                )
            }
            
            HandleButtons(model = model)
          
        }

    }
}

@Composable
fun HandleButtons(model: FreezerModel) {
    with(model) {
        Box(modifier = Modifier.fillMaxWidth()){
        IconButton(
            onClick = { fromStart()}, modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                Icons.Filled.SkipPrevious, "Back", tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
        }

        if (playerIsReady) {
            IconButton(
                onClick = { startPlayer() },
                modifier = Modifier
                    .background(
                        SolidColor(Color.LightGray),
                        shape = CircleShape,
                        alpha = 0.5f
                    )
                    .align(Alignment.Center)
                    .size(72.dp),
            ) {
                Icon(
                    Icons.Filled.PlayArrow, "",
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }
        } else {
            IconButton(
                onClick = { pausePlayer() },
                modifier = Modifier
                    .background(SolidColor(LightGray), shape = CircleShape, alpha = 0.5f)
                    .align(Alignment.Center)
                    .size(72.dp)
            ) {
                Icon(
                    Icons.Filled.Pause, "",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        IconButton(
            onClick = { nextTrack() }, modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Filled.SkipNext, "Next", tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
        }
    }
    }
}