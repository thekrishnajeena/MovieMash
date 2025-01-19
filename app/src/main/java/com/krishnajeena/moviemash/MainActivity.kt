package com.krishnajeena.moviemash

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.krishnajeena.moviemash.data.HomeViewModel
import com.krishnajeena.moviemash.ui.screens.DetailsScreen

import com.krishnajeena.moviemash.ui.screens.MovieScreen
import com.krishnajeena.moviemash.ui.screens.TVScreen
import com.krishnajeena.moviemash.ui.theme.MovieMashTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieMashTheme {

                val navController = rememberNavController()


                val bottomNavigationItems = listOf(
                    BottomNavigationScreens.Movies,
                    BottomNavigationScreens.TVShows
                )

                var selectedItem by rememberSaveable { mutableIntStateOf(0) }
                val selectedIcons = listOf(Icons.Filled.Home, Icons.Filled.Star)
                val unselectedIcons =
                    listOf(Icons.Outlined.Home, Icons.Outlined.Star)

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                           bottomNavigationItems.forEachIndexed{
                               index, item ->
                               NavigationBarItem(
                                   icon = {
                                       Icon(
                                       if (selectedItem == index) selectedIcons[index] else unselectedIcons[index],
                                       contentDescription = item.route
                                       )
                                   },
                                   label = {Text(item.route)},
                                   selected = selectedItem == index,
                                   onClick = {selectedItem = index
                                   navController.navigateUp()}

                               )
                           }
                        }

                    }) { innerPadding ->

                    Box(modifier = Modifier.fillMaxSize().padding(innerPadding),
                        contentAlignment = Alignment.Center){

                        NavHost(navController = navController,startDestination = "MTV") {

                       composable("MTV"){
                           if(selectedItem==0) TVScreen(navController = navController)
                           else MovieScreen(navController = navController)
                       }
                            composable("detailsScreen/{id}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("id")
                                DetailsScreen(navController = navController, id =  id)

                            }
                        }

                    }
                }
            }
        }
    }
}

sealed class BottomNavigationScreens(val route: String) {
    object Movies : BottomNavigationScreens("Movies")
    object TVShows : BottomNavigationScreens("TVShows")
}
