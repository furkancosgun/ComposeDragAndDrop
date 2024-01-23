package com.furkancosgun.composedraganddrop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun BoardContentItem(modifier: Modifier = Modifier, boardItem: BoardItem) {
    DragTarget<BoardItem>(modifier = Modifier, dataToDrop = boardItem) {
        Card(
            modifier = modifier
                .width(350.dp)
                .padding(8.dp),
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .size(50.dp, 10.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    color = Color.Red,
                )
                Text(text = boardItem.title, fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.Home, contentDescription = "")
            }
        }
    }
}
