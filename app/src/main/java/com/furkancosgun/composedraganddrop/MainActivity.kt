package com.furkancosgun.composedraganddrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.furkancosgun.composedraganddrop.ui.theme.ComposeDragAndDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDragAndDropTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val viewModel: MainScreenViewModel = viewModel()
    LongPressDraggable {
        Scaffold { padding ->
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(8.dp)
            ) {
                items(viewModel.state.boards, key = {
                    it.id
                }) { board ->
                    DropTarget<BoardItem>(modifier = Modifier.fillMaxSize()) { isInBound, dropItem ->
                        dropItem?.let {
                            if (isInBound) {
                                viewModel.onDragAndDropFinished(dropItem, board)
                            }
                        }
                        val bgColor = if (isInBound) {
                            Color.Red
                        } else {
                            Color.Unspecified
                        }
                        Column(Modifier.background(bgColor)) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = board.title,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 28.sp,
                            )
                            LazyColumn {
                                items(board.items, key = {
                                    it.id
                                }) { boardItem ->
                                    BoardContentItem(boardItem = boardItem)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MainScreen_Preview() {
    ComposeDragAndDropTheme{
        MainScreen()
    }
}