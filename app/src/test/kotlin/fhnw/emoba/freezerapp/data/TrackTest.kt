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
            {"album": {"id": "302127", "title": "Discovery", "cover_big": "big", "tracklist": "list"
       },
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
        val track = Track(json)

        //then
        with(track) {
            Assert.assertEquals("Discovery", album.title)
            Assert.assertEquals("Happy", title)
            Assert.assertEquals("play", preview)
            Assert.assertEquals("Daft Punk", artist.name)


        }
    }
}