package com.furkancosgun.composedraganddrop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class MainScreenViewModel : ViewModel() {
    var state by mutableStateOf(
        MainScreenState()
    )

    init {
        val boards = mutableListOf<Board>()

        repeat(5) { boardIndex ->
            val boardId = UUID.randomUUID().toString()
            val boardTitle = "Board $boardIndex"
            val boardItems = mutableListOf<BoardItem>()

            repeat(3) { itemIndex ->
                val itemId = UUID.randomUUID().toString()
                val itemTitle = "$boardTitle Item $itemIndex"
                val itemLongText = "$boardTitle Item $itemIndex LongText"

                boardItems.add(BoardItem(itemId, itemTitle, itemLongText))
            }

            boards.add(Board(boardId, boardTitle, boardItems))
        }

        state = state.copy(boards = boards)
    }

    fun onDragAndDropFinished(dropItem: BoardItem, toBoard: Board) {
        // Find the board from which the item was dragged
        val fromBoard = state.boards.find { it.items.contains(dropItem) } ?: return

        if (fromBoard == toBoard) return

        // Remove the item from the source board
        val updatedSourceBoard = fromBoard.copy(
            items = fromBoard.items.toMutableList().apply {
                remove(dropItem)
            }
        )

        // Add the item to the destination board
        val updatedDestinationBoard = toBoard.copy(
            items = toBoard.items.toMutableList().apply {
                add(dropItem)
            }
        )

        // Update the state with the changes
        state = state.copy(
            boards = state.boards.map { board ->
                when (board) {
                    fromBoard -> updatedSourceBoard
                    toBoard -> updatedDestinationBoard
                    else -> board
                }
            }
        )
    }
}
