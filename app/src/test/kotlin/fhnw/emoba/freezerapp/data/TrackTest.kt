package fhnw.emoba.freezerapp.data

import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TrackTest {
    private lateinit var jsonString: String

    @Before
    fun setUp() {
        jsonString = """
            {"album": {"id": "302127", "title": "Discovery"},
            "title": "Happy",
            "preview": "play",
            "artist": {"id": "27", "name": "Daft Punk"},
             }
             """
    }

    @Test
    fun testConstructor() {
        //given
        val json = JSONObject(jsonString)


        //when
        val artist = Track(json)

        //then
        with(artist) {
            Assert.assertEquals("{\"id\":\"302127\",\"title\":\"Discovery\"}", album)
            Assert.assertEquals("happy", title)
            Assert.assertEquals("play", preview)
            Assert.assertEquals("{\"id\": \"27\", \"name\": \"Daft Punk\"}" , artist)


        }
    }
}