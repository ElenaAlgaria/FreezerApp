package fhnw.emoba.freezerapp.data

import org.json.JSONObject
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*


class RadioTest {
    private lateinit var jsonString: String

    @Before
    fun setUp(){
        jsonString = """
            {"title": "80er" ,
            "picture_medium": "pic",
            "tracklist": "radio/tracks/898"
             }
             """
    }

    @Test
    fun testConstructor(){
        //given
        val json = JSONObject(jsonString)

        //when
        val artist = Radio(json)

        //then
        with(artist) {
            assertEquals("80er", title)
            assertEquals("pic", pic)
            assertEquals("radio/tracks/898", tracks)


        }
    }
}