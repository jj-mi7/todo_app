package com.www.todofinal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.www.todofinal.ui.navigation.Navbar
import com.www.todofinal.ui.theme.TodoFinalTheme
import com.www.todofinal.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoFinalTheme {
                val homeViewModel:HomeViewModel by viewModels()
                val intent = intent
                val action = intent.action
                val type = intent.type

                if (Intent.ACTION_SEND == action && "text/plain" == type) {
                    val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                    if (sharedText != null) {
                        homeViewModel.setIntentData(sharedText)
                    }
                }
                Navbar()

            }
        }
    }
}

