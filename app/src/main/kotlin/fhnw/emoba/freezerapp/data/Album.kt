package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import org.json.JSONArray
import org.json.JSONObject

class Album(json: JSONObject) {

    val title = json.getString("title")
    val cover = json.getString("cover_big")
    val tracks = json.getString("tracklist")
   // var loadedImage by mutableStateOf(DEFAULT_ICON)
    var loadedImage : ImageBitmap? = null

}

class AlbumWithArtist(json: JSONObject){
    val title = json.getString("title")
    val cover = json.getString("cover_big")
    val tracks = json.getString("tracklist")
    var loadedImage by mutableStateOf(DEFAULT_ICON)
    var artist = json.getJSONObject("artist")

}