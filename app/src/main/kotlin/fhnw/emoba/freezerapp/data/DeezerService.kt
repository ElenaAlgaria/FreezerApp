package fhnw.emoba.freezerapp.data

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*
import javax.net.ssl.HttpsURLConnection


class DeezerService {
    val baseURL = "https://api.deezer.com"
    val allRadios = mutableListOf<Radio>()



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

    fun getAlbum(url: String): Album{
        // val album = ALbum(song.album as JSONObject) -> album.cover mache
        val url = URL("$baseURL/album/")
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