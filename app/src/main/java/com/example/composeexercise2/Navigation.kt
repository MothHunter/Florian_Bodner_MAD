package com.example.composeexercise2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  "homescreen") {
        composable("homescreen") {HomeScreen(navController = navController)}
        //composable("detailscreen") {DetailScreen(navController = navController)}
    }

}