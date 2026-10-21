package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object AppList : Screen("app_list")
    object Showcase : Screen("showcase")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.AppList.route
    ) {
        composable(Screen.AppList.route) {
            AppListScreen(
                onAppClick = { app -> println("Clicked in list: \${app.name}") }
            )
        }

        composable(Screen.Showcase.route) {
            ShowcaseSection(
                onAppClick = { app -> println("Clicked in showcase: \${app.name}") }
            )
        }
    }
}
