package com.krishnajeena.moviemash.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.krishnajeena.moviemash.data.HomeViewModel
import com.krishnajeena.moviemash.data.ReleaseSuccessResponse
import com.krishnajeena.moviemash.ui.Result
import org.koin.androidx.compose.koinViewModel

@Composable
fun TVScreen(modifier: Modifier = Modifier, navController: NavController) {

    val context = LocalContext.current
    val viewModel: HomeViewModel = koinViewModel()

    val uiState by viewModel.uiState.observeAsState()

    when (uiState) {
        is Result.Loading -> CircularProgressIndicator()
        is Result.Success<*> -> {
            val (movies, tvShows) = (uiState as Result.Success<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>).data

            LazyVerticalGrid(GridCells.Fixed(2)) {
                items(tvShows.titles) { tvShow ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color.Gray)
                            .padding(2.dp), onClick = {navController.navigate("detailsScreen/${tvShow.id}")}
                    ) {
                        Box(modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            Text(tvShow.title)
                        }
                    }


                }

            }
        }
        is Result.Error -> Text("Error: ${(uiState as Result.Error).message}")
        null -> {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun MovieScreen(modifier: Modifier = Modifier, navController: NavController) {

    val context = LocalContext.current

    val viewModel: HomeViewModel = koinViewModel()

    val uiState by viewModel.uiState.observeAsState()

    when (uiState) {
        is Result.Loading -> CircularProgressIndicator()
        is Result.Success<*> -> {
            val (movies, tvShows) = (uiState as Result.Success<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>).data

            LazyVerticalGrid(GridCells.Fixed(2)) {
                items(movies.titles) { movie ->

                   Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color.Gray)
                            .padding(2.dp), onClick = {navController.navigate("detailsScreen/${movie.id}")}
                    ) {
                       Box(modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center){
                        Text(text = movie.title)
                       }
                    }
                }

            }
        }
        is Result.Error -> Text("Error: ${(uiState as Result.Error).message}")
        null -> {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

}

//
//
//@OptIn(ExperimentalGlideComposeApi::class)
//@Composable
//fun HomeScreenRelease(modifier: Modifier = Modifier, navController: NavController) {
//
//    val context = LocalContext.current
//
//    val viewModel: HomeViewModel = koinViewModel()
//
////    LaunchedEffect(Unit){
////        viewModel.fetchData()
////    }
//
//    val uiState by viewModel.uiStateRR.observeAsState()
//    Log.i("TAG:::::", "${uiState.toString()}")
//    when (uiState) {
//        is Result.Loading -> {
//            LazyVerticalGrid(GridCells.Fixed(2)) {
//                items(100){ item ->
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(250.dp)
//                            .background(Color.Gray)
//                            .padding(2.dp)
//                            .shimmer()
//                    ) {
//                        GlideImage(model = null, contentDescription = "poster")
//                    }
//                }
//            }
//        }
//        is Result.Success<*> -> {
//            val release = (uiState as Result.Success<ReleaseSuccessResponse>).data
//
//            if(release.releases != null) {
//                LazyVerticalGrid(GridCells.Fixed(2)) {
//                    items(release.releases) { movie ->
//
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(250.dp)
//                                .background(Color.Gray)
//                                .padding(2.dp)
//                                , onClick = { navController.navigate("detailsScreen") }
//                        ) {
//Text(movie.title)
//                        }
//                    }
//
//                }
//            }
//        }
//        is Result.Error -> Text("Error: ${(uiState as Result.Error).message}")
//        null -> {
//            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//}