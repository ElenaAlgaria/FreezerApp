package fhnw.emoba.freezerapp.model

import fhnw.emoba.freezerapp.data.DeezerService
import org.junit.Test

class ModelTest {
    var service = DeezerService()
    @Test
    fun toggleTrackTest(){
        val model = FreezerModel(service)

    }
}