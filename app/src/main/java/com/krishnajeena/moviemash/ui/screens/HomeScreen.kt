package com.krishnajeena.moviemash.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.krishnajeena.moviemash.R
import com.krishnajeena.moviemash.models.HomeViewModel
import com.krishnajeena.moviemash.data.ReleaseSuccessResponse
import com.krishnajeena.moviemash.ui.Result
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = koinViewModel()

    val uiState by viewModel.uiState.observeAsState()
    var isRefreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        state = rememberPullToRefreshState(),
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.fetchData()
        }
    ) {
        when (uiState) {
            is Result.Loading -> {
                LazyVerticalGrid(GridCells.Fixed(2)) {
                    items(100) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shimmer()
                                .height(250.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {

                                    AsyncImage(
                                        model = R.mipmap.ic_launcher,
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "MovieMash",
                                        modifier = Modifier
                                    )
                                    Text("Loading...", modifier = Modifier.shimmer()
                                        .height(25.dp))
                                }
                            }
                        }
                    }
                }
            }

            is Result.Success<*> -> {
                isRefreshing = false

                val (movies, tvShows) = (uiState as Result.Success<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>).data

                LazyVerticalGrid(GridCells.Fixed(2)) {
                    items(tvShows.titles) { tvShow ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .background(Color.Gray)
                                .padding(2.dp),
                            onClick = { navController.navigate("detailsScreen/${tvShow.id}") }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {

                                    AsyncImage(
                                        model = R.mipmap.ic_launcher,
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "MovieMash",
                                        modifier = Modifier
                                    )
                                    Text(text = tvShow.title, modifier = Modifier.padding(2.dp)
                                        .height(25.dp))
                                }
                            }
                        }
                    }
                }
            }

            is Result.Error -> {
                isRefreshing = false

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Button(onClick = { viewModel.fetchData() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            null -> {
                isRefreshing = false
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = koinViewModel()
    val uiState by viewModel.uiState.observeAsState()

    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            viewModel.fetchData()
            isRefreshing = true
        }
    ) {
        when (uiState) {
            is Result.Loading -> {
                isRefreshing = true
                LazyVerticalGrid(GridCells.Fixed(2)) {
                    items(100) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shimmer()
                                .height(250.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally) {

                                    AsyncImage(
                                        model = R.mipmap.ic_launcher,
                                        contentScale = ContentScale.Crop,
                                        contentDescription = "MovieMash",
                                        modifier = Modifier
                                    )
                                    Text("Loading...", modifier = Modifier.shimmer()
                                        .height(25.dp))
                                }
                            }
                        }
                    }
                }
            }

            is Result.Success<*> -> {
                isRefreshing = false
                val (movies, tvShows) = (uiState as Result.Success<Pair<ReleaseSuccessResponse, ReleaseSuccessResponse>>).data

                LazyVerticalGrid(GridCells.Fixed(2)) {
                    items(movies.titles) { movie ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .background(Color.Gray)
                                .padding(2.dp),
                            onClick = { navController.navigate("detailsScreen/${movie.id}") }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally){
                                AsyncImage(model = R.mipmap.ic_launcher, contentScale = ContentScale.Crop,
                                    contentDescription = "MovieMash", modifier = Modifier)
                                Text(text = movie.title, modifier = Modifier.padding(2.dp)
                                    .height(25.dp))
                                }
                            }
                        }
                    }
                }
            }

            is Result.Error -> {
                isRefreshing = false

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                       Button(onClick = { viewModel.fetchData() }) {
                            Text("Retry")
                        }
                    }
                }
            }

            null -> {
                isRefreshing = false
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
