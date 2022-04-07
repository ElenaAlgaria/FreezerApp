package fhnw.emoba.freezerapp.data

import org.json.JSONObject

class Artist (json: JSONObject) {

    val name = json.getString("name")

    override fun toString(): String {
        return "Artist(name='$name')"
    }


}