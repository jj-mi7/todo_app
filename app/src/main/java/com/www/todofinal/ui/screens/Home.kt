package com.www.todofinal.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.www.todofinal.MainActivity
import com.www.todofinal.data.roomdb.Todo
import com.www.todofinal.viewModel.AddUpdateViewModel
import com.www.todofinal.viewModel.HomeViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController, x: MainActivity) {
    val viewModel = ViewModelProvider(x)[HomeViewModel::class.java]
    val bufferViewModel = ViewModelProvider(x)[AddUpdateViewModel::class.java]
//    val viewModel :HomeViewModel = viewModel()
//    val viewModel = viewModel<HomeViewModel>()
//    val viewModel : HomeViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        if (viewModel.getIntentData().isNotBlank()) {
            navHostController.navigate("edit")
        }
    }


    var showPopup by rememberSaveable {
        mutableStateOf(false)
    }

    var holder by rememberSaveable {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    var term by rememberSaveable {
        mutableStateOf("")
    }

    val searchList by viewModel.searchTodo(term).observeAsState()

    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }

    val keyboardController = LocalSoftwareKeyboardController.current


    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.padding(bottom = 78.dp),
            onClick = {
                navHostController.navigate("edit")
            },
            content = {
                Icon(Icons.Default.Add, contentDescription = "Add")
            },
        )
    }) {
        Text(
            text = "Joel's Todo App",
            modifier = Modifier.padding(120.dp, top = 500.dp),
            fontFamily = FontFamily.Cursive,
            fontSize = 20.sp
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp, bottom = 77.dp, top = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {

                TextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                    value = term,
                    modifier = Modifier.padding(bottom = 10.dp),
                    onValueChange = { term = it },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLeadingIconColor = Color.Red,
                        unfocusedLeadingIconColor = Color.DarkGray

                    ),
                    placeholder = {
                        Text("Search Todos'")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    })
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2), modifier = Modifier.weight(1f)
            ) {
                searchList?.let {
                    items(it.size) { item ->
                        ElevatedCard(colors = CardDefaults.cardColors(containerColor = Color(it[item].color)),
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize()
                                .clickable {
                                    bufferViewModel.setBuffer(it[item])
                                    navHostController.navigate("update")
                                }) {
                            var priority = when (Color(it[item].priority)) {
                                Color.Red -> "High     "
                                Color.Yellow -> "Medium"
                                Color.Green -> "Low      "
                                else -> "High"
                            }

                            Row {
                                var checked = it[item].done
                                Checkbox(checked = checked,
                                    colors = CheckboxDefaults.colors(Color.LightGray),
                                    modifier = Modifier
                                        .padding(
                                            start = 4.dp, top = 4.dp, end = 4.dp
                                        )
                                        .size(28.dp)
                                        .scale(.75f),
                                    onCheckedChange = { change ->
                                        checked = change
                                        viewModel.addTodo(
                                            Todo(
                                                it[item].id,
                                                it[item].title,
                                                it[item].note,
                                                checked,
                                                it[item].dateTime,
                                                it[item].color,
                                                it[item].category,
                                                it[item].progress,
                                                it[item].priority
                                            )
                                        )
                                    })
                                Text(
                                    color = Color.Black,
                                    text = it[item].title,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight(500),

                                    modifier = Modifier
                                        .padding(top = 4.dp, end = 0.dp)
                                        .weight(1f),
                                    fontSize = 15.sp
                                )
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "delete",
                                    tint = Color.DarkGray,
                                    modifier = Modifier
                                        .weight(.25f)
                                        .padding(start = 0.dp, top = 7.dp, end = 4.dp)
                                        .scale(.9f)
                                        .clickable(onClick = {
                                            holder = item
                                            showPopup = true
                                        })
                                )
                                if (showPopup) {
                                    AlertDialog(onDismissRequest = { showPopup = false },
                                        title = { Text(text = "Confirm Deletion") },
                                        text = { Text(text = "Are you sure?") },
                                        confirmButton = {
                                            Button(onClick = {
                                                val dummy = it[holder]
                                                viewModel.dltTodo(it[holder])
                                                showPopup = false
                                                scope.launch {
                                                    val result = snackbarHostState.showSnackbar(
                                                        message = "Revert Deletion",
                                                        actionLabel = "Undo",
                                                        duration = SnackbarDuration.Short
                                                    )
                                                    when (result) {
                                                        SnackbarResult.ActionPerformed -> {
                                                            viewModel.addTodo(dummy)
                                                        }

                                                        SnackbarResult.Dismissed -> {/* Handle snackbar dismissed */
                                                        }
                                                    }
                                                }
                                            }) {
                                                Text(text = "Yes")
                                            }
                                        })
                                }
                            }

                            Text(
                                modifier = Modifier.padding(8.dp),
                                style = TextStyle(
                                    lineHeight = 17.sp // Adjust the line spacing here
                                ),
                                fontSize = 13.sp,
                                fontFamily = FontFamily.Cursive,
                                text = it[item].note,
                                fontWeight = FontWeight(700),
                                color = Color.DarkGray
                            )

                            Row {
                                Text(
                                    text = "Due : ",
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(4.dp)
                                )

                                Text(
                                    modifier = Modifier.padding(start = 0.dp, 4.dp),
                                    fontSize = 10.sp,
                                    text = it[item].dateTime,
                                    color = Color.Blue
                                )

                            }
                            Row {
                                Text(
                                    text = "Category : ",
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 0.dp, 4.dp),
                                    fontSize = 10.sp,
                                    text = it[item].category,
                                    color = Color.Blue
                                )
                            }

                            Row {
                                Text(
                                    text = "Progression :",
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 0.dp, 4.dp),
                                    fontSize = 10.sp,
                                    text = "${it[item].progress.toInt()}%",
                                    color = Color.Blue
                                )
                            }

                            Row {
                                Text(
                                    text = "Priority : ",
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 0.dp, 4.dp),
                                    fontSize = 10.sp,
                                    text = priority,
                                    color = Color(it[item].priority)
                                )

                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(
                                        Intent.EXTRA_TEXT, "${it[item].title}: ${it[item].note}"
                                    )
                                    type = "text/plain"
                                }

                                Icon(
                                    Icons.Default.Share,
                                    contentDescription = "share",
                                    tint = Color.DarkGray,
                                    modifier = Modifier
//                                        .weight(.5f)
                                        .padding(start = 25.dp, bottom = 4.dp, end = 2.dp)
                                        .scale(.85f)
                                        .clickable(onClick = {
                                            startActivity(context, sendIntent, null)

                                        })
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


