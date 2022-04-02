package fhnw.emoba.freezerapp.ui

import androidx.constraintlayout.compose.Dimension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.model.AvailableTab
import fhnw.emoba.freezerapp.model.FreezerModel
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun AppUI(model: FreezerModel) {
    with(model) {
        loadDeezerAsync()
        MaterialTheme(
            colors = lightColors(
                primary = Color(142, 183, 171),
                surface = Color(0xFFF8F7F3),
                onPrimary = Color(0xFF343434),
                secondary = Color(0xFF347880)
            )
        ) {

            Scaffold(topBar = { TopBar(model) },
                content = { Body(model) },
                bottomBar = { BottomBar(model) })
        }

    }
}


@Composable
fun Body(model: FreezerModel) {
    with(model) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (selectedTab == AvailableTab.SEARCH) {
                search(model)
            } else if (selectedTab == AvailableTab.PLAYER) {
                setupPlayer(model)
            }
        }
    }
}

@Composable
fun setupRadio(model: FreezerModel){
    with(model){
        model.radio
    }
}

@Composable
fun setupPlayer(model: FreezerModel) {
    with(model) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxSize()) {
            IconButton(
                onClick = { "todo" }, modifier = Modifier.align(Alignment.Top)
            ) {
                Icon(
                    Icons.Filled.SkipPrevious, "Back", tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
            }
            if (playerIsReady) {
                IconButton(
                    onClick = { startPlayer() },
                    modifier = Modifier
                        .background(
                            SolidColor(Color.LightGray),
                            shape = CircleShape,
                            alpha = 1.0f
                        )
                        .align(Alignment.CenterVertically)
                        .size(72.dp),
                ) {
                    Icon(
                        Icons.Filled.PlayArrow, "",
                        tint = Color.Black,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }else{
                IconButton(
                    onClick = { pausePlayer() },
                    modifier = Modifier
                        .background(Color.LightGray, shape = CircleShape)
                        .size(72.dp)
                ) {
                    Icon(
                        Icons.Filled.Pause, "",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

    }
    }


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


    @Composable
    fun TopBar(model: FreezerModel) {
        with(model) {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.deezerlogo),
                        contentDescription = "logo", alignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    )
                },
                modifier = Modifier
                    .background(color = Color(142, 183, 171))
                    .fillMaxWidth()
            )

        }
    }

    @Composable
    fun BottomBar(model: FreezerModel) {
        with(model) {
            Column() {
                TabRow(selectedTabIndex = selectedTab.ordinal) {
                    for (tab in AvailableTab.values()) {
                        Tab(text = { Text(tab.title) },
                            selected = tab == selectedTab,
                            onClick = { selectedTab = tab }
                        )
                    }
                }
            }
        }
    }
