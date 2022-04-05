package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel

@Composable
fun setupPlayer(model: FreezerModel) {
    with(model) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()) {

            Text(text = trackName, fontWeight = FontWeight.Bold, modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp))
            
            Image(painter = , contentDescription = )
            
                HandleButtons(model = model)
          
        }

    }
}

@Composable
fun HandleButtons(model: FreezerModel) {
    with(model) {
        Box(){
        IconButton(
            onClick = { "todo" }, modifier = Modifier.align(Alignment.CenterStart)
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
                        alpha = 1.0f
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
                    .background(Color.LightGray, shape = CircleShape)
                    .size(72.dp)
            ) {
                Icon(
                    Icons.Filled.Pause, "",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        IconButton(
            onClick = { "todo" }, modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Filled.SkipNext, "Next", tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
        }
    }
    }
}