package fhnw.emoba.freezerapp.data

import org.json.JSONObject

data class Track (
    val id: Int,
    val title: String,

        ){
    constructor(json: JSONObject): this(
        id = json.getInt("id"),
        title = json.getString("title")
    )

    override fun toString(): String {
        return "Song(id=$id, title='$title')"
    }


}