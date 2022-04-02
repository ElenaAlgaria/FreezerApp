package fhnw.emoba.freezerapp.data

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection


class DeezerService {

    fun requestDeezer(id: Int){
    val baseURL = "https://api.deezer.com"
    val url = URL("$baseURL/radio/$id")
    content(url)


    }
}