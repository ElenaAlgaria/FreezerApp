package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.data.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONObject

class FreezerModel (val ser: DeezerService){
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var currentPlaying = ""
    private var preview = ""
    var trackName = ""

    var currentScreen by mutableStateOf(AvailableScreen.EXPLORE)
    var searchWord by mutableStateOf("")
    var playerIsReady by mutableStateOf(true)

    var isLoading by mutableStateOf(false)
    var radioList: List<Radio> by mutableStateOf(emptyList())
    var trackRadioList: List<Track> by mutableStateOf(emptyList())

/*
    var favoriteRadios: List<Radio> by mutableStateOf(
        listOf(
            repo.getRadio(37151)
        )
    )

 */

    fun loadDeezerAsync() {
        isLoading = true
        radioList = emptyList()
        modelScope.launch {
            val resultList = ser.requestDeezerRadio()
            radioList = resultList

            for (i in 0 until radioList.size){
                val radio = radioList.get(i)
                val image = ser.getImageFromRadio(radio.pic)
                radio.loadedImage = image
            }

            isLoading = false
        }
    }

    fun loadRadioTracks(tracks: String) {
        modelScope.launch {
            val tracksResult = ser.getRadioTracks(tracks)
            trackRadioList = tracksResult
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

    fun setTrack(preview: String, trackName: String, album: JSONObject){
        this.preview = preview
        this.trackName = trackName
        album.
        startPlayer()

    }

    fun startPlayer() {
        playerIsReady = false
        if (currentPlaying == preview && !player.isPlaying){
            player.start()
        } else {
            currentPlaying = preview
            player.setDataSource(preview)
            player.prepareAsync()
        }
    }

    fun pausePlayer(){
        player.pause()
        playerIsReady = true
    }
}