package fhnw.emoba.freezerapp.data

import org.json.JSONObject

class Track (json: JSONObject)

{
       val id = json.getInt("id")
       val title = json.getString("title")
       val preview = json.getString("preview")
       val album = json.getJSONObject("album")
       val artist = json.getJSONObject("artist")
       var fav = false


    override fun toString(): String {
        return "Song(id=$id, title='$title')"
    }


}