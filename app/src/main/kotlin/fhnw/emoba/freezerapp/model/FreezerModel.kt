package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.DeezerRepository
import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.data.Radio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FreezerModel (val repo : DeezerRepository, val ser: DeezerService){
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val radio : Radio
    get() = repo.radio

    var selectedTab by mutableStateOf(AvailableTab.RADIO)
    var searchWord by mutableStateOf("")
    var playerIsReady by mutableStateOf(true)

    var isLoading by mutableStateOf(false)
    var radioList: List<Radio> by mutableStateOf(emptyList())

    var favoriteRadios: List<Radio> by mutableStateOf(
        listOf(
            repo.getRadio(37151),
            repo.getRadio(30771),
            repo.getRadio(41782),
            repo.getRadio(31061),
            repo.getRadio(38305)
        )
    )

    fun loadDeezerAsync() {
      isLoading = true
      radioList = emptyList()
      radioList.forEach{
        modelScope.launch {
            ser.requestDeezer(it.id)
            isLoading = false
        }
      }
    }


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