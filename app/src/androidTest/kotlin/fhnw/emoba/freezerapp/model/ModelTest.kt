package fhnw.emoba.freezerapp.model

import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import org.junit.Assert
import org.junit.Test

class ModelTest {
    var service = DeezerService()

    @Test
    fun launchSearchTest(){
        val model = FreezerModel(service)
        model.launchSearch()
        Thread.sleep(100)

        Assert.assertNotNull(model.resultTrackList)
        Assert.assertEquals(0, model.resultAlbumList.size)
    }

    @Test
    fun launchSearchAlbumTest(){
        val model = FreezerModel(service)
        model.rememberAlbum = true
        model.rememberTrack = false

        model.launchSearch()
        Thread.sleep(100)

        Assert.assertNotNull(model.resultAlbumList)
        Assert.assertEquals(0, model.resultTrackList.size)
    }
}