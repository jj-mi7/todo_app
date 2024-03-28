package com.www.todofinal.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.www.todofinal.MainActivity
import com.www.todofinal.data.roomdb.Todo
import com.www.todofinal.viewModel.AddUpdateViewModel
import com.www.todofinal.viewModel.HomeViewModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Edit(navController: NavHostController, x: MainActivity) {

    val viewModel2 = ViewModelProvider(x)[AddUpdateViewModel::class.java]
    val homeViewModel = ViewModelProvider(x)[HomeViewModel::class.java]

    var sliderPosition by remember { mutableFloatStateOf(0f) }

    val context = LocalContext.current

    var note by rememberSaveable {
        mutableStateOf(homeViewModel.getIntentData())
    }

    homeViewModel.setIntentData("")

    var title by rememberSaveable {
        mutableStateOf("")
    }

    var date by rememberSaveable {
        mutableStateOf("")
    }

    val state = rememberDatePickerState()

    val openDialog = rememberSaveable { mutableStateOf(false) }

    val itemsPriority = listOf("Work", "Personal")

    var selectedItemPriority by rememberSaveable { mutableStateOf(itemsPriority[0]) }

    var expandedPriority by rememberSaveable { mutableStateOf(false) }

    var category by rememberSaveable {
        mutableStateOf(itemsPriority[0])
    }

    val items = listOf("High ", "Medium", "Low")

    var selectedItem by rememberSaveable { mutableStateOf(items[0]) }

    var expanded by rememberSaveable { mutableStateOf(false) }

    var priorityColor by rememberSaveable {
        mutableStateOf(Color.Red.toArgb())
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Add Todo",
                        maxLines = 1,
                        fontWeight = FontWeight(800),
                        fontStyle = FontStyle.Italic,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    )
    {
        it

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(20.dp)
        )
        {
            Column(modifier = Modifier.verticalScroll(rememberScrollState()))
            {
                OutlinedTextField(
                    shape = RoundedCornerShape(20.dp),
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier
                        .padding(start = 10.dp, top = 25.dp, end = 10.dp, bottom = 10.dp)
                        .wrapContentSize()
                        .fillMaxWidth(),
                    textStyle = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
                        .wrapContentSize()
                        .fillMaxWidth(),
                    value = note,
                    onValueChange = { note = it },
                    label = { Text(text = "Enter Description") },
                    shape = RoundedCornerShape(20.dp),
                )

                Button(
                    onClick = { openDialog.value = true },
                    modifier = Modifier.padding(10.dp)
                )
                {
                    Text(text = "Pick Due Date")
                }
                if (openDialog.value) {
                    DatePickerDialog(onDismissRequest = {
                        openDialog.value = false
                    },
                        confirmButton = {
                            TextButton(onClick = {
                                openDialog.value = false
                            }
                            )
                            {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                openDialog.value = false
                            }
                            )
                            {
                                Text("CANCEL")
                            }
                        }
                    )
                    {
                        DatePicker(
                            state = state
                        )
                    }
                }

                date = millisToDate(state.selectedDateMillis ?: 0L)

                if (date == "01-01-1970") {
                    date = ""
                }



                Column(
                    modifier = Modifier.padding(10.dp)
                )
                {
                    OutlinedButton(onClick = { expandedPriority = true }
                    )
                    {
                        Text("Category : ${selectedItemPriority}")
                    }
                    DropdownMenu(modifier = Modifier.background(
                        color = Color.White, shape = RectangleShape
                    ),
                        expanded = expandedPriority,
                        onDismissRequest = { expandedPriority = false }) {
                        itemsPriority.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item) }, onClick = {
                                selectedItemPriority = item
                                category = item
                                expandedPriority = false
                            }
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.padding(10.dp)
                )
                {
                    OutlinedButton(onClick = { expanded = true }
                    )
                    {
                        Text("Priority : ${selectedItem}")
                    }
                    DropdownMenu(modifier = Modifier.background(
                        color = Color.White, shape = RectangleShape
                    ),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        items.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item) }, onClick = {
                                selectedItem = item
                                priorityColor = when (selectedItem) {
                                    items[0] -> Color.Red.toArgb()
                                    items[1] -> Color.Yellow.toArgb()
                                    items[2] -> Color.Green.toArgb()
                                    else -> {
                                        Color.Red.toArgb()
                                    }
                                }
                                expanded = false
                            }
                            )
                        }
                    }
                }
                Column {
                    Text(
                        text = "Progression: ${sliderPosition.toInt()}",
                        fontWeight = FontWeight(700),
                        modifier = Modifier.padding(start = 15.dp, 10.dp),
                        fontFamily = FontFamily.Monospace
                    )

                    Slider(
                        modifier = Modifier
                            .padding(5.dp)
                            .scale(.80f),
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        steps = 100,
                        colors = SliderDefaults.colors(
                            thumbColor = MaterialTheme.colorScheme.primary,
                            activeTrackColor = MaterialTheme.colorScheme.secondary,
                            inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        valueRange = 0f..100f
                    )
                }
            }

            ExtendedFloatingActionButton(modifier = Modifier
                .padding(start = 230.dp, top = 20.dp, bottom = 20.dp),
                text = { Text(text = "Save") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "saveicon"
                    )
                },
                onClick = {
                    if (title.isNotBlank() and note.isNotBlank() and date.isNotBlank()) {
                        viewModel2.addTodo(
                            Todo(
                                title = title,
                                note = note,
                                done = false,
                                dateTime = date,
                                color = generateRandomLightColor(),
                                category = category,
                                progress = sliderPosition,
                                priority = priorityColor
                            )
                        )
                        Toast.makeText(context, "Created Todo", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                    if (title.isBlank() or note.isBlank() or date.isBlank()) {
                        Toast.makeText(context, "Enter all fields", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

}

fun generateRandomLightColor(): Int {
    val random = Random.Default
    val red = random.nextInt(200, 256) // Ensure the red component is in the range [200, 255]
    val green = random.nextInt(200, 256) // Ensure the green component is in the range [200, 255]
    val blue = random.nextInt(200, 256) // Ensure the blue component is in the range [200, 255]
    return Color(red, green, blue).toArgb()
}

fun millisToDate(millis: Long): String {
    val instant = Instant.ofEpochMilli(millis)
    val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy") // Specify your desired date format here
    return date.format(formatter)
}