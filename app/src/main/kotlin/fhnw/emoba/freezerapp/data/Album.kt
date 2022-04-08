package fhnw.emoba.freezerapp.data

import androidx.compose.ui.graphics.ImageBitmap
import org.json.JSONArray
import org.json.JSONObject

class Album(json: JSONObject) {

    val id = json.getInt("id")
    val title = json.getString("title")
    val cover = json.getString("cover_big")
    val tracks = json.getString("tracklist")
    var loadedImage: ImageBitmap? = null


    override fun toString(): String {
        return "Album(id=$id, title='$title', cover='$cover', tracks=$tracks)"
    }
}

class AlbumWithArtist(json: JSONObject) {

    val id = json.getInt("id")
    val title = json.getString("title")
    val cover = json.getString("cover_big")
    var loadedImage: ImageBitmap? = null
    val tracks = json.getString("tracklist")
    val artist = json.getJSONObject("artist")


}