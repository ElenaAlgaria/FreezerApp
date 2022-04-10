package fhnw.emoba.freezerapp

import androidx.test.platform.app.InstrumentationRegistry
import fhnw.emoba.freezerapp.data.DeezerService
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
    fun getTracksWithAlbum(){
        val service = DeezerService()

        val tracks = service.getTracks("https://api.deezer.com/radio/30771/tracks")

        Assert.assertEquals(25, tracks.size)
    }

    @Test
    fun getTracksNoAlbumTest(){
        val service = DeezerService()

        val tracks = service.getTracks("https://api.deezer.com/album/248216622/tracks")

        Assert.assertEquals(16, tracks.size)
    }


}