package com.metehanbolat.bottomnavigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.metehanbolat.bottomnavigationcompose.screen.AccountScreen
import com.metehanbolat.bottomnavigationcompose.screen.HomeScreen
import com.metehanbolat.bottomnavigationcompose.screen.SearchScreen
import com.metehanbolat.bottomnavigationcompose.screen.SettingsScreen

@Composable
fun NavHostController(navController: NavHostController) {
    
    NavHost(navController = navController, startDestination = Screen.Home.route) {

        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Account.route) { AccountScreen() }
        composable(Screen.Search.route) { SearchScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }

    }

}