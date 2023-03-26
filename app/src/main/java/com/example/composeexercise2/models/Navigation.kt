package com.example.composeexercise2

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeexercise2.models.DETAIL_ARGUMENT_KEY
import com.example.composeexercise2.models.Screen
import com.example.composeexercise2.screens.DetailScreen
import com.example.composeexercise2.screens.FavoriteScreen
import com.example.composeexercise2.screens.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        // home screen
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController
            )
        }
        // detail screen
        composable(
            route = Screen.DetailScreen.route,
            // optional: define Type of argument
            arguments = listOf(navArgument(DETAIL_ARGUMENT_KEY) {
                type = NavType.StringType
            })
        ) {
            backStackEntry ->
            DetailScreen(navController = navController,
                movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY))
        }
        // favorites screen
        composable(
            Screen.FavoriteScreen.route
        ) {
            FavoriteScreen(navController = navController
            )
        }
    }

}