package fhnw.emoba.freezerapp.service

import androidx.test.platform.app.InstrumentationRegistry
import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.model.FreezerModel
import org.junit.Assert

import org.junit.Test

class DeezerServiceTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        // Context of the app under test.
        Assert.assertEquals("fhnw.emoba", appContext.packageName)
    }

    @Test
    fun testGetRadio() {
        //given
        val radioService = DeezerService()

        //when
        val radio = radioService.requestRadio()

        //then
        Assert.assertEquals(25, radio.size)

    }

    @Test
    fun searchTrackTest(){
        val service = DeezerService()

        val track = service.requestSearchTrack("happier than ever")

        Assert.assertNotNull(track)
    }

    @Test
    fun searchAlbumTest(){
        val service = DeezerService()

        val album = service.requestSearchTrack("happier than ever")

        Assert.assertNotNull(album)
    }


    @Test
    fun getTracksRadio(){
        val service = DeezerService()
         val radioList = service.requestRadio()

        val tracks = service.getTracks(radioList.get(0))

        Assert.assertEquals(25, tracks.size)
    }

    @Test
    fun getTracksAlbumTest(){
        val service = DeezerService()
        val albumList = service.requestSearchAlbum("happier than ever")

        val tracks = service.getTracks(albumList.get(0))

        Assert.assertEquals(16, tracks.size)
    }


}