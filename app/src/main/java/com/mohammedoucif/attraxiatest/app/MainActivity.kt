package com.mohammedoucif.attraxiatest.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mohammedoucif.attraxiatest.chatsFeature.ui.FirstUserChatsScreen
import com.mohammedoucif.attraxiatest.chatsFeature.ui.SecondUserChatsScreen
import com.mohammedoucif.attraxiatest.designSystem.theme.AttraxiatestTheme
import com.mohammedoucif.attraxiatest.messagingFeature.ui.FirstUserMessagingScreen
import com.mohammedoucif.attraxiatest.messagingFeature.ui.FirstUserMessagingScreenRoute
import com.mohammedoucif.attraxiatest.messagingFeature.ui.SecondUserMessagingScreen
import com.mohammedoucif.attraxiatest.messagingFeature.ui.SecondUserMessagingScreenRoute

class MainActivity : ComponentActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttraxiatestTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                var isBottomNavigationBarVisible by remember {
                    mutableStateOf(true)
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding(),
                    bottomBar = {
                        BottomNavigationBar(
                            currentScreen = currentDestination,
                            navController = navController,
                            isVisible = isBottomNavigationBarVisible
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Destination.FirstUserChatsScreen
                    ) {
                        composable<Destination.FirstUserChatsScreen> {
                            FirstUserChatsScreen(
                                onChatClick = {
                                    navController.navigate(
                                        FirstUserMessagingScreenRoute(it)
                                    ) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }

                                        restoreState = true
                                    }
                                }
                            )
                            isBottomNavigationBarVisible = true
                        }

                        composable<Destination.SecondUserChatsScreen> {
                            SecondUserChatsScreen(
                                onChatClick = {
                                    navController.navigate(
                                        SecondUserMessagingScreenRoute(it)
                                    )
                                }
                            )
                            isBottomNavigationBarVisible = true
                        }

                        composable<FirstUserMessagingScreenRoute> {
                            FirstUserMessagingScreen()
                            isBottomNavigationBarVisible = false
                        }

                        composable<SecondUserMessagingScreenRoute> {
                            SecondUserMessagingScreen()
                            isBottomNavigationBarVisible = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentScreen: NavDestination?,
    navController: NavController,
    isVisible: Boolean
) {
    if(isVisible) {
        NavigationBar {
            BottomNavigationItems.entries.forEachIndexed { _, item ->
                NavigationBarItem(
                    selected = currentScreen?.hierarchy?.any {
                        it.hasRoute(item.route::class) } == true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(item.title)
                    },
                    icon = {
                        Icon(
                            imageVector = if (currentScreen?.hierarchy?.any {
                                    it.hasRoute(
                                        item.route::class
                                    )
                                } == true) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}