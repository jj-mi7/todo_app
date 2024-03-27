package com.www.todofinal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.www.todofinal.ui.screens.Navbar
import com.www.todofinal.ui.theme.TodoFinalTheme
import com.www.todofinal.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoFinalTheme {
                val x=this
//                val VIVE : HomeViewModel by viewModels()
                Navbar(x)

            }
        }
    }


}

