package com.example.composeexercise2


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeexercise2.models.Movie
import com.example.composeexercise2.models.getMovies
import com.example.composeexercise2.ui.theme.ComposeExercise2Theme


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

@Composable
fun HomeAppBar(navController: NavController) {
    TopAppBar {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Movies", fontSize = 24.sp)
            Spacer(modifier = Modifier.weight(1f))
            var expanded by remember { mutableStateOf(false) }
            // var selectedIndex by remember { mutableStateOf(0) }
            Box {
                IconButton(
                    onClick = {
                        expanded = !expanded
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Open Options"
                    )
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        navController.navigate(route = "favscreen")
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
            }
        }
    }
}

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier.padding(vertical = 5.dp).clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Column() {

            Box(
                contentAlignment = Alignment.TopEnd,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .aspectRatio(1.6f)
            ) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = "Scene from ${movie.title}",
                    modifier = Modifier.fillMaxSize(1f),
                    contentScale = ContentScale.Crop
                )
                ToggleIcon(Icons.Rounded.FavoriteBorder, Icons.Rounded.Favorite)
            }
            AnimatedMovieNameRow(movie = movie)
        }
    }
}

@Composable
fun AnimatedMovieNameRow(movie: Movie) {
    var visible by remember { mutableStateOf(false) }
    Column() {

        // This is used to disable the ripple effect
        val interactionSource = remember {
            MutableInteractionSource()
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(movie.title, fontSize = 24.sp)
            Spacer(Modifier.weight(1f))

            // horrible code duplication! work out how to modify ToggleIcon so that state can be
            // read from the outside! (pass something with "remember" as additional parameter??)
            IconToggleButton(checked = visible, onCheckedChange = { visible = it }) {
                Icon(
                    imageVector = if (visible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "Movie Details",
                    modifier = Modifier
                        .clickable(
                            indication = null, // Assign null to disable the ripple effect
                            interactionSource = interactionSource
                        ) {
                            visible = !visible
                        }
                        .size(32.dp)
                )
            }
        }
        AnimatedVisibility(visible = visible) {
            MovieDetails(movie)
        }
    }
}

@Composable
fun MovieDetails(movie: Movie) {
    Column() {
        Text(text = "Director: ${movie.director}")
        Text(text = "Released: ${movie.year}")
        Text(text = "Genre: ${movie.genre}")
        Text(text = "Actors: ${movie.actors}")
        Text(text = "Rating: ${movie.rating}")
        Divider(startIndent = 8.dp, thickness = 1.dp, color = Color.Gray)
        Text(text = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            append("Plot: ")
            pop()   // stop applying style from above
            append(movie.plot)
        })
    }
}


// source: https://semicolonspace.com/jetpack-compose-button/
@Composable
fun ToggleIcon(iconStart: ImageVector, iconToggled: ImageVector) {
    var toggleState by remember {
        mutableStateOf(false)
    }

    // This is used to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }

    IconToggleButton(checked = toggleState, onCheckedChange = { toggleState = it }) {
        Icon(
            imageVector = if (toggleState) iconToggled else iconStart,
            contentDescription = "Favorite Movie",
            modifier = Modifier
                .clickable(
                    indication = null, // Assign null to disable the ripple effect
                    interactionSource = interactionSource
                ) {
                    toggleState = !toggleState
                }
                .size(32.dp)
        )
    }
}
