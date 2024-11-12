package com.mohammedoucif.attraxiatest.messagingFeature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohammedoucif.attraxiatest.R
import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.MessageCard
import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageSide
import com.mohammedoucif.attraxiatest.designSystem.component.messageItem.model.MessageUIModel
import com.mohammedoucif.attraxiatest.designSystem.theme.AttraxiatestTheme
import com.mohammedoucif.attraxiatest.messagingFeature.presentation.MessagingViewModel
import com.mohammedoucif.attraxiatest.messagingFeature.presentation.ScreenState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun FirstUserMessagingScreen(
    viewModel: MessagingViewModel = koinViewModel(named("User1_ViewModel"))
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    MessagingScreenUI(
        onSendButtonClick = {
            viewModel.sendMessage(it)
        },
        screenState = screenState
    )
}

@Composable
fun SecondUserMessagingScreen(
    viewModel: MessagingViewModel = koinViewModel(named("User2_ViewModel"))
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    MessagingScreenUI(
        onSendButtonClick = {
            viewModel.sendMessage(it)
        },
        screenState = screenState
    )
}

@Composable
fun MessagingScreenUI(
    onSendButtonClick: (String) -> Unit,
    screenState: ScreenState
) {

    val messagesListState = rememberLazyListState()

    when (screenState) {
        ScreenState.LoadingState -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is ScreenState.SuccessState -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (screenState.isMessagesListEmpty) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.no_messages),
                            modifier = Modifier.align(
                                Alignment.Center
                            )
                        )
                    }
                } else {

                    LaunchedEffect(screenState.messages) {
                        messagesListState.animateScrollToItem(
                            0
                        )
                    }

                    LazyColumn(
                        state = messagesListState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(
                            vertical = 8.dp,
                            horizontal = 8.dp
                        ),
                        reverseLayout = true
                    ) {
                        items(
                            items = screenState.messages,
                            key = { message ->
                                message.id
                            }
                        ) { messageItem ->
                            MessageCard(messageItem)
                        }
                    }
                }

                MessageTextFieldUI {
                    onSendButtonClick(it)
                }
            }
        }

        ScreenState.NetworkErrorState -> {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(R.string.network_message)
                )
            }
        }
    }
}

@Composable
fun MessageTextFieldUI(
    onSendButtonClick: (String) -> Unit
) {
    var message by remember {
        mutableStateOf("")
    }

    val isSendButtonEnabled by remember {
        derivedStateOf {
            message.isNotEmpty()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = message,
            onValueChange = {
                message = it
            },
            placeholder = {
                Text("Type your message ...")
            }
        )

        IconButton(
            onClick = {
                onSendButtonClick(message)
                message = ""
            },
            enabled = isSendButtonEnabled
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = stringResource(R.string.send_message_button)
            )
        }
    }
}

@Composable
@PreviewLightDark
fun MessagingScreenUIPreview() {
    AttraxiatestTheme {
        Surface {
            MessagingScreenUI(
                onSendButtonClick = {},
                screenState = ScreenState.SuccessState(
                    messages = listOf(
                        MessageUIModel(
                            "12IOUR",
                            "User 1",
                            "User 2",
                            "Salam Alykom w rahmato allahi w barakatoh",
                            true,
                            MessageSide.END,
                            "11/11/2024 12:30"
                        ),
                        MessageUIModel(
                            "98YEID",
                            "User 2",
                            "User 1",
                            "w Alaykom salam w rahmato allahi w barakatoh",
                            true,
                            MessageSide.START,
                            "11/11/2024 12:35"
                        )
                    )
                )
            )
        }
    }
}