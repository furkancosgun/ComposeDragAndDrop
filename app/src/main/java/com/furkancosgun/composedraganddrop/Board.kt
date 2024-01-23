package com.furkancosgun.composedraganddrop

data class Board(
    val id: String,
    val title: String,
    var items: List<BoardItem>
)

data class BoardItem(
    val id: String,
    val title: String,
    val longText: String,
)