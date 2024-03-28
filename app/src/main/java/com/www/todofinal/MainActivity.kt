package com.www.todofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.www.todofinal.ui.screens.Navbar
import com.www.todofinal.ui.theme.TodoFinalTheme
import dagger.hilt.android.AndroidEntryPoint

var intentData:String =""

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoFinalTheme {
                val intent = intent
                val action = intent.action
                val type = intent.type

                if (Intent.ACTION_SEND == action && "text/plain" == type) {
                    // Handle the received text data
                    val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
                    if (sharedText != null) {
                        intentData=sharedText
                    }
                }
                val x=this
//                val VIVE : HomeViewModel by viewModels()
                Navbar(x)

            }
        }
    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        intent?.let {
//            if (Intent.ACTION_SEND == it.action && "text/plain" == it.type) {
//                val sharedText = it.getStringExtra(Intent.EXTRA_TEXT)
//                if (sharedText != null) {
//                    intentData=sharedText
//                    Log.d("alaga", intentData)
//
//
//                }
//            }
//        }
//
//    }


}

