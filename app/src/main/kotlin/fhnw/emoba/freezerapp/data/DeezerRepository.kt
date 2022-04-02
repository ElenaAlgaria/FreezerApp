package fhnw.emoba.freezerapp.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.asImageBitmap
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.*
import javax.net.ssl.HttpsURLConnection

class DeezerRepository(context: Context)  {

    lateinit var radio: Radio
    val data = dataListFrom() { Radio(it) }


    fun getRadio(id: Int) : Radio = data.filter{it.id == id}.first()






    }
