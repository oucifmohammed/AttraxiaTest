package com.mohammedoucif.attraxiatest.chatsFeature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohammedoucif.attraxiatest.R
import com.mohammedoucif.attraxiatest.chatsFeature.presentation.ChatsViewModel
import com.mohammedoucif.attraxiatest.chatsFeature.presentation.ScreenState
import com.mohammedoucif.attraxiatest.designSystem.component.chatItem.ChatItem
import com.mohammedoucif.attraxiatest.designSystem.component.chatItem.model.ChatUIModel
import com.mohammedoucif.attraxiatest.designSystem.theme.AttraxiatestTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

@Composable
fun FirstUserChatsScreen(
    onChatClick: (chatId: String) -> Unit,
    viewModel: ChatsViewModel = koinViewModel(named("User1_ViewModel"))
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    ChatsScreenUI(
        onChatClick = { onChatClick(it) },
        onAddChatButtonClick = { viewModel.addChat(it) },
        screenState = screenState
    )
}

@Composable
fun SecondUserChatsScreen(
    onChatClick: (chatId: String) -> Unit,
    viewModel: ChatsViewModel = koinViewModel(named("User2_ViewModel"))
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    ChatsScreenUI(
        onChatClick = { onChatClick(it) },
        onAddChatButtonClick = { viewModel.addChat(it) },
        screenState = screenState
    )
}

@Composable
fun ChatsScreenUI(
    onChatClick: (chatId: String) -> Unit,
    onAddChatButtonClick: (String) -> Unit,
    screenState: ScreenState
) {

    var isAddChatDialogVisible by remember {
        mutableStateOf(false)
    }

    if(isAddChatDialogVisible) {
        AddChatDialog {
            onAddChatButtonClick(it)
            isAddChatDialogVisible = false
        }
    }

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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    )
                ) {
                    items(
                        items = screenState.chats,
                        key = { chat ->
                            chat.id
                        }
                    ) { chatItem ->
                        ChatItem(
                            onClick = {
                                onChatClick(chatItem.id)
                            },
                            chatItem = chatItem
                        )
                    }
                }

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 8.dp, bottom = 8.dp),
                    onClick = {
                        isAddChatDialogVisible = true
                    }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add_chat_button)
                    )
                }
            }
        }

        is ScreenState.NetworkErrorState -> {
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
fun AddChatDialog(
    onAddButtonClick: (String) -> Unit
) {

    var chatName by remember {
        mutableStateOf("")
    }

    val isAddButtonEnabled by remember {
        derivedStateOf {
            chatName.isNotEmpty()
        }
    }

    Dialog(onDismissRequest = {}) {
        Card(
            modifier = Modifier
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 16.dp
                    )
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.add_chat),
                    style = MaterialTheme.typography.headlineMedium
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    value = chatName,
                    onValueChange = {
                        chatName = it
                    },
                    label = {
                        Text("Type the chat name ...")
                    }
                )

                Button(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        onAddButtonClick(chatName)
                    },
                    enabled = isAddButtonEnabled
                ) {
                    Text(
                        stringResource(R.string.add)
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
fun AddChatDialogPreview() {
    AttraxiatestTheme {
        AddChatDialog(
            onAddButtonClick = {}
        )
    }
}

@PreviewLightDark
@Composable
fun FirstUserScreenUIPreview() {
    AttraxiatestTheme {
        Surface {
            ChatsScreenUI(
                onChatClick = {},
                onAddChatButtonClick = {},
                screenState = ScreenState.SuccessState(
                    chats = listOf(
                        ChatUIModel(
                            "Chat1",
                            "Sport",
                            1
                        ),
                        ChatUIModel(
                            "Chat2",
                            "Cinema",
                            3
                        ),
                        ChatUIModel(
                            "Chat3",
                            "News",
                            6
                        )
                    )
                )
            )
        }
    }
}