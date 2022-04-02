package fhnw.emoba.freezerapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.data.DeezerRepository
import fhnw.emoba.freezerapp.data.DeezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.AppUI


object FreezerApp : EmobaApp {
    private lateinit var model: FreezerModel

    override fun initialize(activity: ComponentActivity) {
        val repo = DeezerRepository(activity)
        val ser = DeezerService()
        model = FreezerModel(repo, ser)

    }

    @Composable
    override fun CreateUI() {
        AppUI(model)
    }

}