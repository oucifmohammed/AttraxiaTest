package com.mohammedoucif.attraxiatest.designSystem.component.messageItem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageSide
import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageUIModel
import com.mohammedoucif.attraxiatest.designSystem.theme.AttraxiatestTheme

@Composable
fun MessageCard(
    messageItem: MessageUIModel,
    modifier: Modifier = Modifier
) {
    Column {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = when (messageItem.messageSide) {
                        MessageSide.START -> {
                            0.dp
                        }

                        MessageSide.END -> {
                            40.dp
                        }
                    },
                    end = when (messageItem.messageSide) {
                        MessageSide.START -> {
                            40.dp
                        }

                        MessageSide.END -> {
                            0.dp
                        }
                    }
                ),
            shape = CutCornerShape(
                topStart = when (messageItem.messageSide) {
                    MessageSide.START -> {
                        10.dp
                    }

                    MessageSide.END -> {
                        0.dp
                    }
                },
                topEnd = when (messageItem.messageSide) {
                    MessageSide.START -> {
                        0.dp
                    }

                    MessageSide.END -> {
                        10.dp
                    }
                }
            ),
            colors = CardDefaults.cardColors(
                containerColor = when (messageItem.messageSide) {
                    MessageSide.START -> {
                        MaterialTheme.colorScheme.primary
                    }

                    MessageSide.END -> {
                        MaterialTheme.colorScheme.secondary
                    }
                }
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            ) {
                Text(
                    text = messageItem.senderId
                )

                Text(
                    text = messageItem.content
                )

                if (messageItem.messageSide == MessageSide.END) {
                    if (messageItem.isSeen) {
                        Icon(
                            modifier = Modifier.align(Alignment.End),
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Is message seen icon",
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            modifier = Modifier.align(Alignment.End),
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = "Is message seen icon",
                            tint = Color.Red
                        )
                    }
                }
            }
        }

        Text(
            modifier = Modifier.align(
                when (messageItem.messageSide) {
                    MessageSide.START -> {
                        Alignment.Start
                    }

                    MessageSide.END -> {
                        Alignment.End
                    }
                }
            ),
            text = messageItem.timeSent,
            color = Color.Red
        )
    }
}

@PreviewLightDark
@Composable
private fun MessageItemSenderPreview() {
    AttraxiatestTheme {
        MessageCard(
            messageItem = MessageUIModel(
                "12IOUR",
                "User 1",
                "User 2",
                "Salam Alaykom w rahmato allahi w barakatoh",
                false,
                MessageSide.END,
                "11/11/2024 12:35"
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun MessageItemReceiverPreview() {
    AttraxiatestTheme {
        MessageCard(
            messageItem = MessageUIModel(
                "12IOUR",
                "User 2",
                "User 1",
                "Salam Alaykom w rahmato allahi w barakatoh",
                true,
                MessageSide.START,
                "11/11/2024 12:30"
            )
        )
    }
}