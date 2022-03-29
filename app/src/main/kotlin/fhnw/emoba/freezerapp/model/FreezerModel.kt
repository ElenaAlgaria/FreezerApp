package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object FreezerModel {
    var title = "Hello Deezer"
    var selectedTab by mutableStateOf(AvailableTab.RADIO)
    var searchWord by mutableStateOf("")
    var playerIsReady by mutableStateOf(true)


    private val player = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        setOnPreparedListener {
            start()
        }
        setOnCompletionListener { playerIsReady = true }
    }

    fun startPlayer() {
        playerIsReady = false
        TODO("Not yet implemented")
    }

    fun pausePlayer(){
        player.pause()
        playerIsReady = true
    }
}