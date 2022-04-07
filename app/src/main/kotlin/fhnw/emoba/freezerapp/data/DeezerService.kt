package fhnw.emoba.freezerapp.data

import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection


class DeezerService(activity: ComponentActivity) {
    val baseURL = "https://api.deezer.com"
    val allRadios = mutableListOf<Radio>()
    val context = activity
    val allTracks = mutableListOf<Track>()
    val allAlben = mutableListOf<Album>()


    fun getData(url: URL): JSONArray {
        val conn = url.openConnection() as HttpsURLConnection
        val inputStream = conn.inputStream
        conn.connect()

        val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
        val jsonString = reader.readText()
        reader.close()

        val jsonObject = JSONObject(jsonString)
        val data = jsonObject.getJSONArray("data")
        return data
    }

    fun getAlbumCover(url: String): ImageBitmap {
        try{

        val url = URL(url)
        val conn = url.openConnection() as HttpsURLConnection
        conn.connect()

        val inputStream = conn.inputStream
        var allBytes = inputStream.readBytes()
        inputStream.close()

        val bitmap = BitmapFactory.decodeByteArray(allBytes, 0, allBytes.size)

        return bitmap.asImageBitmap()
        } catch (e: Exception){
            return BitmapFactory.decodeResource(context.resources, R.drawable.deezerlogo).asImageBitmap()
        }
    }

    fun requestSearchTrack(word: String): List<Track>{
        val url = URL("$baseURL/search/track?q=$word")
        var data = getData(url)

        for (i in 0 until data.length()){
            val json = data.get(i)
            allTracks.add(Track(json as JSONObject))
        }

        return allTracks
    }

    fun requestSearchAlbum(word: String): List<Album>{
        val url = URL("$baseURL/search/album?q=$word")
        var data = getData(url)

        for (i in 0 until data.length()){
            val json = data.get(i)
            allAlben.add(Album(json as JSONObject))
        }
        return allAlben
    }



    fun requestDeezerRadio(): List<Radio> {
        val url = URL("$baseURL/radio/lists")
        var data = getData(url)

        for (i in 0 until data.length()) {
            val json = data.get(i)
            allRadios.add(Radio(json as JSONObject))
        }
        return allRadios
    }

    fun requestArtistOfTrack(artist: Artist){

    }

    fun getRadioTracks(trackList: String): List<Track> {
        val url = URL(trackList)
        var data = getData(url)
        val allTracksRadio = mutableListOf<Track>()

        for (i in 0 until data.length()) {
            val json = data.get(i)
            allTracksRadio.add(Track(json as JSONObject))
        }
        return allTracksRadio
    }

    fun getImageFromRadio(url: String): ImageBitmap {
        val url = URL(url)
        val conn = url.openConnection() as HttpsURLConnection
        conn.connect()

        val inputStream = conn.inputStream
        var allBytes = inputStream.readBytes()
        inputStream.close()

        val bitmap = BitmapFactory.decodeByteArray(allBytes, 0, allBytes.size)

        return bitmap.asImageBitmap()
    }
}