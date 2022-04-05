package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import fhnw.emoba.freezerapp.model.FreezerModel



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun search(model: FreezerModel) {
    with(model) {
        ConstraintLayout(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
        {
            val (link, searchField) = createRefs()
            OutlinedTextField(
                value = searchWord,
                onValueChange = { searchWord = it },
                placeholder = { Text("Search") },
                modifier = Modifier.constrainAs(searchField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(link.bottom, 20.dp)
                    width = Dimension.fillToConstraints
                },
                trailingIcon = {
                    IconButton(onClick = {
                        searchWord = ""
                    }) { Icon(Icons.Filled.Clear, "") }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Ascii
                ),
                keyboardActions = KeyboardActions(onDone = {
                    // keyboard?.hide()
                    //model.loadFlag()
                })
            )
        }
    }
}
