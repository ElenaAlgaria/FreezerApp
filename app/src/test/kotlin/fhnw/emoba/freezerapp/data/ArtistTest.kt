package fhnw.emoba.freezerapp.data

import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArtistTest {
    private lateinit var jsonString: String

    @Before
    fun setUp(){
        jsonString = """
            {"name": "Billie" 
             }
             """
    }

    @Test
    fun testConstructor(){
        //given
        val json = JSONObject(jsonString)

        //when
        val artist = Artist(json)

        //then
        with(artist) {
            Assert.assertEquals("Billie", name)


        }
    }

}
