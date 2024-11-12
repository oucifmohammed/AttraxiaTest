package com.mohammedoucif.attraxiatest.designSystem.component.chatItem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mohammedoucif.attraxiatest.designSystem.component.chatItem.model.ChatUIModel
import com.mohammedoucif.attraxiatest.designSystem.theme.AttraxiatestTheme

@Composable
fun ChatItem(
    onClick: (chatId: String) -> Unit,
    chatItem: ChatUIModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onClick(chatItem.id) }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
                text = chatItem.name
            )

            if (chatItem.areAllMessagesSeen) {
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = "${chatItem.newMessagesNumber}",
                    color = Color.Red
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ChatItemPreview() {
    AttraxiatestTheme {
        ChatItem(
            onClick = {},
            chatItem = ChatUIModel(
                "Chat2",
                "Cinema",
                3
            )
        )
    }
}