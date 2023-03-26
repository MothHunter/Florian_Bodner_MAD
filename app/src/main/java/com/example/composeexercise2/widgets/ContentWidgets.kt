package com.example.composeexercise2.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.composeexercise2.models.Movie
import com.example.composeexercise2.models.Screen

@Composable
fun MovieRow(movie: Movie, onItemClick: (String) -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clickable { onItemClick(movie.id) },
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
                ToggleIcon(Icons.Rounded.FavoriteBorder,
                    Icons.Rounded.Favorite,
                    remember {
                        mutableStateOf(false)
                    })
            }
            AnimatedMovieNameRow(movie = movie)
        }
    }
}

@Composable
fun AnimatedMovieNameRow(movie: Movie) {
    var visible = remember { mutableStateOf(false) }
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(movie.title, fontSize = 24.sp)
            Spacer(Modifier.weight(1f))
            ToggleIcon(iconStart = Icons.Default.KeyboardArrowUp,
                iconToggled = Icons.Default.KeyboardArrowDown,
                toggleState = visible)
        }
        AnimatedVisibility(visible = visible.value) {
            MovieInfoBox(movie)
        }
    }
}

@Composable
fun MovieInfoBox(movie: Movie) {
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



@Composable
fun ImageRow(movie: Movie) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        items(movie.images) { movieImage ->
            Card(modifier = Modifier.fillMaxHeight(1f)) {
                AsyncImage(
                    model = movieImage,
                    contentDescription = "Scene from ${movie.title}",
                    modifier = Modifier.fillMaxHeight(1f),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}

@Composable
fun ScrollableMovieColumn (navController: NavController, movies: List<Movie>) {
    LazyColumn {
        items(movies) { movie ->
            MovieRow(movie) { movieID ->
                navController.navigate(route = Screen.DetailScreen.passId(movieID))
            }
        }
    }
}