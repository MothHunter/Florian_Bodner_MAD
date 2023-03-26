package com.example.composeexercise2.models

const val DETAIL_ARGUMENT_KEY = "movieId"

sealed class Screen (val route: String) {
    object HomeScreen: Screen(route = "homescreen")
    object DetailScreen: Screen(route = "detailscreen/{$DETAIL_ARGUMENT_KEY}") {  // route with navArgument
        fun passId(movieId: String): String {
            return this.route.replace("{$DETAIL_ARGUMENT_KEY}", movieId)
        }
    }
    object FavoriteScreen: Screen(route = "favscreen")
}