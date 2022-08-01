package com.metehanbolat.bottomnavigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.metehanbolat.bottomnavigationcompose.ui.theme.BottomNavigationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {

    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Account, Screen.Search, Screen.Settings)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(
                            imageVector = screen.icon,
                            contentDescription = "Bottom Navigation Item Icon"
                        )},
                        label = { Text(text = screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                /** Hangi fragmentte olursa olsun start destination'a döner ve aynı menüye birden fazla kez basıldığında çalışmasını engeller. */
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                NavHostController(navController = navController)
            }
        }
    )
}
