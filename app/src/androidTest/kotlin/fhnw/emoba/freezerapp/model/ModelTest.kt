package fhnw.emoba.freezerapp.model

import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import org.junit.Assert
import org.junit.Test

class ModelTest {

    @Test
    fun launchSearchTest(){
        var service = DeezerService()
        val model = FreezerModel(service)
        model.launchSearch()

        Assert.assertNotNull(model.resultTrackList)
        Assert.assertEquals(0, model.resultAlbumList.size)
    }

    @Test
    fun launchSearchAlbumTest(){
        var service = DeezerService()
        val model = FreezerModel(service)
        model.rememberAlbum = true
        model.rememberTrack = false

        model.launchSearch()

        Assert.assertNotNull(model.resultAlbumList)
        Assert.assertEquals(0, model.resultTrackList.size)
    }


}