package com.krishnajeena.moviemash.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.request.RequestOptions
import com.krishnajeena.moviemash.R
import com.krishnajeena.moviemash.data.Details
import com.krishnajeena.moviemash.data.DetailsViewModel
import org.koin.androidx.compose.koinViewModel
import com.krishnajeena.moviemash.ui.Result
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreen(modifier: Modifier = Modifier, navController: NavHostController, id: String?) {

    val context = LocalContext.current
    val viewModel: DetailsViewModel = koinViewModel()


    val uiState by viewModel.uiState.observeAsState()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.fetchDetails(id)
        }
    }

    when(uiState){
        is Result.Loading -> {
            Column(modifier = Modifier
                .fillMaxSize()){
                //  Details(details = details)
                GlideImage(model = null, contentDescription = null, modifier = Modifier.fillMaxWidth().fillMaxHeight(.4f)
                    .shimmer()
                    )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)){
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center)
                    {
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text("", modifier = Modifier.fillMaxWidth(.6f).shimmer())
                            Text("", modifier = Modifier.fillMaxWidth(.6f).shimmer())
                        }
                    }

                    Text("", modifier = Modifier.fillMaxWidth(.5f).shimmer())
                    Text("", modifier = Modifier.fillMaxWidth().fillMaxHeight(.4f).shimmer())
                }

            }
        }
        is Result.Success -> {
            val details = (uiState as Result.Success).data

            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){

                val scrollState = rememberScrollState()
                if (id != null) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)){
                  //  Details(details = details)
                    GlideImage(model = details.poster, contentScale = ContentScale.FillWidth, contentDescription = details.originalTitle,
                        )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)){
                        Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center)
                    {
                        Column(modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Text(details.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text("Release Data: ${details.releaseDate}")
                        }
                    }

                        Text("Description", fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth().
                        padding(start = 2.dp))
                        Text("${details.plotOverview}", modifier = Modifier.padding(2.dp))
                }

                    }
                }

            }
        }
        is Result.Error -> {
            Text("Error: ${(uiState as Result.Error).message}")
        }

        null -> {}
    }



}