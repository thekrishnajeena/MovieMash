package com.krishnajeena.moviemash.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.krishnajeena.moviemash.models.DetailsViewModel
import com.krishnajeena.moviemash.ui.Result
import com.valentinilk.shimmer.shimmer
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: String?
) {
    val viewModel: DetailsViewModel = koinViewModel()
    val uiState by viewModel.uiState.observeAsState()
    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.fetchDetails(id)
        }
    }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = {
            if (id != null) {
                viewModel.fetchDetails(id)
                isRefreshing = true
            }
        }
    ) {
        when (uiState) {
            is Result.Loading -> {
                isRefreshing = true
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shimmer()
                            .height(450.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Loading...", modifier = Modifier.shimmer())
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "           ",
                                    modifier = Modifier
                                        .fillMaxWidth(0.6f)
                                        .shimmer()
                                )
                                Text(
                                    "          ",
                                    modifier = Modifier
                                        .fillMaxWidth(0.6f)
                                        .shimmer()
                                )
                            }
                        }

                        Text(
                            "         ",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .shimmer()
                        )
                        Text(
                            "          \n\n\n\n",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.4f)

                                .shimmer()
                        )
                    }
                }
            }

            is Result.Success -> {
                isRefreshing = false
                val details = (uiState as Result.Success).data
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    GlideImage(
                        model = details.poster,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = details.originalTitle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 400.dp)

                    )


                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    details.title,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text("Release Date: ${details.releaseDate}")
                            }
                        }

                        Text(
                            "Description",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 2.dp)
                        )
                        Text(
                            "${details.plotOverview}",
                            modifier = Modifier.padding(2.dp)
                        )
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
                        Button(onClick = { if (id != null) viewModel.fetchDetails(id)
                        isRefreshing=true}) {
                            Text("Retry")
                        }
                    }
                }
            }

            null -> {
                isRefreshing = false
            }
        }
    }
}
