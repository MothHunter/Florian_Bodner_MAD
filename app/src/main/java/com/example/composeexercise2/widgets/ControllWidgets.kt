package com.example.composeexercise2.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composeexercise2.models.Screen

@Composable
fun SimpleAppBar(navController: NavController, label: String) {
    TopAppBar {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back",
                modifier = Modifier.clickable(
                    onClick = {
                        navController.popBackStack()
                    })
            )
            Text(label, fontSize = 24.sp)
        }
    }
}

@Composable
fun HomeAppBar(navController: NavController) {
    TopAppBar {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Movies", fontSize = 24.sp)
            Spacer(modifier = Modifier.weight(1f))
            // var selectedIndex by remember { mutableStateOf(0) }
            OptionsMenu(navController = navController)
        }
    }
}

@Composable
fun OptionsMenu (navController: NavController) {
    var expanded = remember { mutableStateOf(false) }
    Box {
        IconButton(
            onClick = {
                expanded.value = !expanded.value
            },
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Open Options"
            )
        }
        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            FavMenuItem(navController = navController, parentExpanded = expanded)
        }
    }
}

@Composable
fun FavMenuItem(navController: NavController, parentExpanded: MutableState<Boolean>) {
    DropdownMenuItem(onClick = {
        parentExpanded.value = false
        navController.navigate(route = Screen.FavoriteScreen.route)
    }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "Fav icon"
            )
            Text(" Favorites", fontSize = 24.sp)
        }
    }
}

// source: https://semicolonspace.com/jetpack-compose-button/
@Composable
fun ToggleIcon(iconStart: ImageVector, iconToggled: ImageVector, toggleState: MutableState<Boolean>) {
    // This is used to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    IconToggleButton(checked = toggleState.value, onCheckedChange = { toggleState.value = it }) {
        Icon(
            imageVector = if (toggleState.value) iconToggled else iconStart,
            contentDescription = "Favorite Movie",
            modifier = Modifier
                .clickable(
                    indication = null, // Assign null to disable the ripple effect
                    interactionSource = interactionSource
                ) {
                    toggleState.value = !toggleState.value
                }
                .size(32.dp)
        )
    }
}