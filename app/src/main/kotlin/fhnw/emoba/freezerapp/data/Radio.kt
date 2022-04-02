package fhnw.emoba.freezerapp.data

import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

data class Radio (
    val id: Int,
    val title: String,
    val pic: String,
    //eig URL
    val trackList: JSONArray
    )
{

    constructor(json: JSONObject) : this(
        id = json.getInt("id"),
        title = json.getString("title"),
        pic = json.getString("picture"),
        trackList = json.getJSONArray("tracklist")
    )

    constructor(jsonString: String) : this(JSONObject(jsonString))

    override fun toString(): String {
        return "Radio(title='$title', pic='$pic')"
    }


}