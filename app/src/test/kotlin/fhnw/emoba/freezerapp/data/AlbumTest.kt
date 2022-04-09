package fhnw.emoba.freezerapp.data

import org.json.JSONObject
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*


internal class AlbumTest{
    private lateinit var jsonString: String
    
    @Before
    fun setUp(){
        jsonString = """
            {"title": "Happier than ever",
              "cover_big": "big",
             "tracklist": "/album/list/777",
             
             }
             """
    }
    
    @Test
    fun testConstructor(){
        //given
        val json = JSONObject(jsonString)
        
        //when
        val alb = Album(json)
        
        //then
        with(alb) {
            assertEquals("Happier than ever",  title)
            assertEquals("big", cover)
            assertEquals("/album/list/777",       tracks)

        }
    }

}