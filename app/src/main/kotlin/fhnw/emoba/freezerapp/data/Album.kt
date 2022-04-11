package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import org.json.JSONArray
import org.json.JSONObject

class Album(json: JSONObject, artist: Artist? = null) {

    val title = json.getString("title")
    val cover = json.getString("cover_big")
    val tracks = json.getString("tracklist")
    var loadedImage by mutableStateOf(DEFAULT_ICON)
    var artist = if(artist != null) artist else Artist(json.getJSONObject("artist"))
}
