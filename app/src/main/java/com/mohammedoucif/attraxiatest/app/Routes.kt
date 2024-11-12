package com.mohammedoucif.attraxiatest.app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object FirstUserChatsScreen : Destination

    @Serializable
    data object SecondUserChatsScreen : Destination
}

enum class BottomNavigationItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Destination
) {
    User(
        "User",
        Icons.Filled.Person,
        Icons.Outlined.Person,
        Destination.FirstUserChatsScreen
    ),
    Friend(
        "Friend",
        Icons.Filled.MailOutline,
        Icons.Outlined.MailOutline,
        Destination.SecondUserChatsScreen
    )
}