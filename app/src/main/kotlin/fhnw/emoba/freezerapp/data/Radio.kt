package fhnw.emoba.freezerapp.data

import androidx.compose.ui.graphics.ImageBitmap
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

 class Radio (json: JSONObject)
{
       val id = json.getInt("id")
       val title = json.getString("title")
       val pic = json.getString("picture_medium")
       val tracks = json.getString("tracklist")
       var loadedImage : ImageBitmap? = null


    constructor(radio: Radio, jsonString: String) : this (JSONObject(jsonString))

    override fun toString(): String {
        return "Radio(title='$title', pic='$pic')"
    }


}