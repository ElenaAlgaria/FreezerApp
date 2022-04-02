package fhnw.emoba.freezerapp.data

import android.R.attr.data
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection


fun content(url: URL): String = content(streamFrom(url))

fun content(inputStream: InputStream): String {
    val reader = BufferedReader(InputStreamReader(inputStream, StandardCharsets.UTF_8))
    val jsonString = reader.readText()
    reader.close()

    return jsonString
}

fun <T> dataListFrom(url: URL, transform: (JSONObject) -> T): List<T> =
    JSONArray(content(url)).map(transform)



fun bitmap(inputStream: InputStream): Bitmap {
    val bitmapAsBytes = inputStream.readBytes()
    inputStream.close()

    return BitmapFactory.decodeByteArray(bitmapAsBytes, 0, bitmapAsBytes.size)
}

fun <T> JSONArray.map(transform: (JSONObject) -> T): List<T> {
    val list = mutableListOf<T>()
    for (i in 0 until length()) {
        list.add(transform(getJSONObject(i)))
    }
    return list
}

fun streamFrom(url: URL): InputStream {
    val conn = url.openConnection() as HttpsURLConnection
    conn.connect()

    return conn.inputStream
}