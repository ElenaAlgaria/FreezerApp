package fhnw.emoba.freezerapp.data

import androidx.compose.ui.graphics.ImageBitmap
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

 class Radio (json: JSONObject)
{
       val title = json.getString("title")
       val pic = json.getString("picture_medium")
       val tracks = json.getString("tracklist")
       lateinit var loadedImage : ImageBitmap

    override fun toString(): String {
        return "Radio(title='$title', pic='$pic')"
    }


}