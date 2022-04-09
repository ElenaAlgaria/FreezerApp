package fhnw.emoba.freezerapp.data

import org.json.JSONObject

class Track(json: JSONObject) {
    var album = json.getJSONObject("album")
    val title = json.getString("title")
    val preview = json.getString("preview")
    val artist = json.getJSONObject("artist")

}

class TracksNoAlbum(json: JSONObject) {
    val title = json.getString("title")
    val preview = json.getString("preview")
    val artist = json.getJSONObject("artist")

}

