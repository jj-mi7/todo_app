package com.www.todofinal.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

data  class NavBarClass(
    val route:String,
    val title:String,
    val sicon: ImageVector,
    val usicon: ImageVector
    )

val items= listOf(

    NavBarClass(route="home",
        title = "Home",
        sicon = Icons.Filled.Home,
        usicon = Icons.Outlined.Home),

    NavBarClass(route="completed",
        title = "Completed",
        sicon = Icons.Filled.Done,
        usicon = Icons.Outlined.Done),

    NavBarClass(route="category",
        title = "Category",
        sicon = Icons.Filled.List,
        usicon = Icons.Outlined.List),
)

