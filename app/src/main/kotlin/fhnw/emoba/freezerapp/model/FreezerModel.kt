package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class FreezerModel(val ser: DeezerService) {
    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var currentPlaying = ""
    private var preview = ""
    var trackName = ""
    var album: Album? = null
    var artist: Artist? = null

    var currentScreen by mutableStateOf(AvailableScreen.EXPLORE)
    var searchWord by mutableStateOf("")
    var playerIsReady by mutableStateOf(true)

    var isLoading by mutableStateOf(false)
    var radioList: List<Radio> by mutableStateOf(emptyList())
    var trackList: List<Track> by mutableStateOf(emptyList())
    var tracksFavorites = mutableListOf<Track>()

    var resultTrackList: List<Track> by mutableStateOf(emptyList())
    var resultAlbumList: List<AlbumWithArtist> by mutableStateOf(emptyList())

    var rememberTrack by mutableStateOf(true)
    var rememberAlbum by mutableStateOf(false)


/*
random album hole
    var favoriteRadios: List<Radio> by mutableStateOf(
        listOf(
            repo.getRadio(37151)
        )
    )

 */
    fun toggleTrack(){
        rememberTrack = !rememberTrack
        rememberAlbum = true
    }

    fun toggleAlbum(){
        rememberAlbum = !rememberAlbum
        rememberTrack = true

    }

    fun launchSearch() {
        modelScope.launch {
            if (rememberTrack) {
                val results = ser.requestSearchTrack(searchWord)
                resultTrackList = results
            }
            if (rememberAlbum){

                val results = ser.requestSearchAlbum(searchWord)
                resultAlbumList = results
            }
        }
    }

    fun loadRadio() {
        isLoading = true
        radioList = emptyList()
        modelScope.launch {
            val resultList = ser.requestDeezerRadio()
            radioList = resultList

            for (i in 0 until radioList.size) {
                val radio = radioList.get(i)
                val image = ser.getImageFromRadio(radio.pic)
                radio.loadedImage = image
            }

            isLoading = false
        }
    }

    fun loadTracks(tracks: String) {
        modelScope.launch {
            val tracksResult = ser.getTracks(tracks)
            trackList = tracksResult
        }
    }

    fun loadAlbumCover(albumObject: JSONObject) {
        modelScope.launch {
            val alb = Album(albumObject)
            album = alb
            val image = ser.getAlbumCover(album!!.cover)
            album!!.loadedImage = image
        }
    }

    fun loadArtist(artistObject: JSONObject): String {
        val art = Artist(artistObject)
        artist = art
        return artist!!.name
    }

    fun setTrack(preview: String, trackName: String, albumObject: JSONObject, artistObject: JSONObject) {
        this.preview = preview
        this.trackName = trackName
        loadAlbumCover(albumObject)
        loadArtist(artistObject)
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
        if (currentPlaying == preview && !player.isPlaying) {
            player.start()
        } else {
            currentPlaying = preview
            player.reset()
            player.setDataSource(preview)
            player.prepareAsync()
        }
    }

    fun pausePlayer() {
        player.pause()
        playerIsReady = true
    }
}

