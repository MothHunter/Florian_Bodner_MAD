package com.example.composeexercise2.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.composeexercise2.models.getMovies
import com.example.composeexercise2.ui.theme.ComposeExercise2Theme
import com.example.composeexercise2.widgets.HomeAppBar
import com.example.composeexercise2.widgets.MovieRow


@Composable
fun HomeScreen(navController: NavController) {
    ComposeExercise2Theme {
        Column {
            HomeAppBar(navController = navController)
            LazyColumn {
                items(getMovies()) { movie ->
                    MovieRow(movie) { movieID ->
                        navController.navigate(route = "detailscreen/$movieID")
                    }
                }
            }
        }
    }
}