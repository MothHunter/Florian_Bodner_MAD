package com.example.composeexercise2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeexercise2.models.Movie
import com.example.composeexercise2.models.getMovies
import com.example.composeexercise2.ui.theme.ComposeExercise2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExercise2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieList(getMovies())
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card() {
        Column() {

                Box(contentAlignment = Alignment.TopEnd) {
                    Image(
                        painter = painterResource(id = R.mipmap.avatar2),
                        contentDescription = movie.title,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ToggleIcon(Icons.Rounded.FavoriteBorder, Icons.Rounded.Favorite)
                }


            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(movie.title, fontSize = 24.sp)
                Spacer(Modifier.weight(1f))
                ToggleIcon(iconStart = Icons.Default.KeyboardArrowUp, iconToggled = Icons.Default.KeyboardArrowDown)
            }
        }
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



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExercise2Theme {
        MovieList(getMovies())
    }
}

@Composable
fun MovieList(movies: List<Movie>) {
    ComposeExercise2Theme {
        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie)
            }
        }
    }
}

