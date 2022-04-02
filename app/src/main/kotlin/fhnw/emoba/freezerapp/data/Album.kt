package fhnw.emoba.freezerapp.data

import org.json.JSONArray
import org.json.JSONObject

data class Album (
    val id: Int,
    val title: String,
    val cover: String,
    val tracks: JSONArray
        ){
    constructor(json: JSONObject) : this(
        id = json.getInt("id"),
        title = json.getString("title"),
        cover = json.getString("cover"),
        tracks= json.getJSONArray("tracklist")
    )

    override fun toString(): String {
        return "Album(id=$id, title='$title', cover='$cover', tracks=$tracks)"
    }


}