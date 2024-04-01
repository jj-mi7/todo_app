package com.www.todofinal.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.www.todofinal.MainActivity
import com.www.todofinal.ui.screens.Category
import com.www.todofinal.ui.screens.Completed
import com.www.todofinal.ui.screens.Edit
import com.www.todofinal.ui.screens.Home
import com.www.todofinal.ui.screens.UpdateScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navbar() {


    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->

                    NavigationBarItem(
                        selected = currentRoute == item.route,

                        label = { Text(text = item.title) },

                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },

                        icon = {
                            Icon(
                                imageVector =
                                if (currentRoute == item.route) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        }
                    )
                }
            }
        }
    )
    {

        NavHost(navController = navController, startDestination = "home")
        {
            composable("home") { Home(navController) }

            composable("edit") { Edit(navController) }

            composable("completed") { Completed(navController) }

            composable("update") { UpdateScreen(navController) }

            composable("category") { Category(navController) }

        }
    }
}