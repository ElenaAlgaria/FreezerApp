package fhnw.emoba.freezerapp.data

import org.json.JSONObject

class Track(json: JSONObject, album: Album? = null) {

    val artist = Artist(json.getJSONObject("artist"))
    var album = if(album != null) album else Album(json.getJSONObject("album"), artist)
    val title = json.getString("title")
    val preview = json.getString("preview")

}


