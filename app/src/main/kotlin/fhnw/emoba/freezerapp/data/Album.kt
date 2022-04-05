package fhnw.emoba.freezerapp.data

import org.json.JSONArray
import org.json.JSONObject

class Album (json: JSONObject){

       val id = json.getInt("id")
       val title = json.getString("title")
       val cover = json.getString("cover_medium")
       val tracks= json.getJSONArray("tracklist")

    override fun toString(): String {
        return "Album(id=$id, title='$title', cover='$cover', tracks=$tracks)"
    }


}