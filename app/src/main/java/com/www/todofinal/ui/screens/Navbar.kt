package com.www.todofinal.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.www.todofinal.MainActivity
import com.www.todofinal.data.roomdb.Todo


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navbar(x: MainActivity) {


    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach {  item ->

                NavigationBarItem(
                    selected = currentRoute==item.route,

                    label = { Text(text = item.title) },

                    onClick = {
                        navController.navigate(item.route)  {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                              },

                    icon = {
                        Icon(imageVector =
                        if(currentRoute==item.route)
                        { item.sicon }
                        else
                        { item.usicon},
                            contentDescription =item.title )
                    }
                )
            }
        }
    }
    )
    {
        NavHost(navController = navController, startDestination = "home")
        {
            composable("home")  {Home(navController,x) }

            composable("edit"){ Edit(navController,x)}

            composable("completed"){ Completed(navController,x)}

            composable("update") { UpdateScreen(navController,x)}

            composable("category") { Category(navController,x) }

        }
    }
}