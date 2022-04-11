package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FreezerModel(val ser: DeezerService) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var currentPlaying = ""
    var track by mutableStateOf<Track?>(null)
    var album by mutableStateOf<Album?>(null)

    var currentScreen by mutableStateOf(AvailableScreen.EXPLORE)
    var searchWord by mutableStateOf("")
    var playerIsReady by mutableStateOf(true)

    var isLoading by mutableStateOf(false)
    var radioList: List<Radio> by mutableStateOf(emptyList())
    var trackList: List<Track> by mutableStateOf(emptyList())

    var tracksFavorites = mutableStateListOf<Track>()

    var resultTrackList: List<Track> by mutableStateOf(emptyList())
    var resultAlbumList: List<Album> by mutableStateOf(emptyList())

    var rememberTrack by mutableStateOf(true)
    var rememberAlbum by mutableStateOf(false)

    var index by mutableStateOf(0)

    fun toggleTrack() {
        rememberTrack = !rememberTrack
        rememberAlbum = true
    }

    fun toggleAlbum() {
        rememberAlbum = !rememberAlbum
        rememberTrack = true

    }

    fun launchSearch() {
        modelScope.launch {
            if (rememberTrack) {
                val results = ser.requestSearchTrack(searchWord)
                resultTrackList = results
            }
            if (rememberAlbum) {
                val results = ser.requestSearchAlbum(searchWord)
                resultAlbumList = results
            }
        }
    }

    fun loadRadio() {
        isLoading = true
        radioList = emptyList()
        modelScope.launch {
            val resultList = ser.requestRadio()
            radioList = resultList

            for (i in 0 until radioList.size) {
                val radio = radioList.get(i)
                val image = ser.getImageFromRadio(radio.pic)
                radio.loadedImage = image
            }
            isLoading = false
        }
    }

    fun loadTracks(radio: Radio) {
        modelScope.launch {
            val tracksResult = ser.getTracks(radio)
            trackList = tracksResult

            for (i in 0 until trackList.size) {
                loadAlbumCover(trackList.get(i).album)
            }
        }
    }

    fun loadTracks(album: Album) {
        modelScope.launch {
            val tracksResult = ser.getTracks(album)
            trackList = tracksResult

            loadAlbumCover(album)
        }
    }

    fun loadAlbumCover(alb: Album) {
        modelScope.launch {
            album = alb
            val image = ser.getAlbumCover(alb.cover)
            alb.loadedImage = image
        }
    }

    fun setTrack(
        index: Int,
        track: Track,
        album: Album
    ) {
        this.track = track
        this.index = index
        loadAlbumCover(album)
        startPlayer()
    }

    fun switchFavorite(track: Track) {
        if (!tracksFavorites.contains(track)) {
            tracksFavorites.add(track)
        } else {
            tracksFavorites.remove(track)
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
        if (currentPlaying == track!!.preview && !player.isPlaying) {
            player.start()
        } else {
            player.reset()
            player.setDataSource(track!!.preview)
            player.prepareAsync()
        }
    }

    fun pausePlayer() {
        player.pause()
        playerIsReady = true
    }

    fun fromStart() {
        player.seekTo(0)
        player.start()
        playerIsReady = false
    }

    fun nextTrack() {
        player.pause()
        val next = trackList.get(++index)
        setTrack(index, next, next.album)
       // currentScreen = AvailableScreen.PLAYER
    }

}

