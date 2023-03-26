package com.example.composeexercise2.screens


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.composeexercise2.models.getMovies
import com.example.composeexercise2.ui.theme.ComposeExercise2Theme
import com.example.composeexercise2.widgets.HomeAppBar
import com.example.composeexercise2.widgets.ScrollableMovieColumn


@Composable
fun HomeScreen(navController: NavController) {
    ComposeExercise2Theme {
        Column {
            HomeAppBar(navController = navController)
            ScrollableMovieColumn(navController = navController, movies = getMovies())
        }
    }
}