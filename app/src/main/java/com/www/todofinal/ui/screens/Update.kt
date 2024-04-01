package com.www.todofinal.ui.screens

import android.util.Log
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.www.todofinal.MainActivity
import com.www.todofinal.data.roomdb.Todo
import com.www.todofinal.viewModel.AddUpdateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(navController: NavHostController) {

    val viewModel4: AddUpdateViewModel = hiltViewModel()

    var sliderPosition by rememberSaveable { mutableFloatStateOf(viewModel4.getBuffer().progress) }

    var note by rememberSaveable {
        mutableStateOf(viewModel4.getBuffer().note)
    }

    var category by rememberSaveable {
        mutableStateOf(viewModel4.getBuffer().category)
    }

    var title by rememberSaveable {
        mutableStateOf(viewModel4.getBuffer().title)
    }

    var date by rememberSaveable {
        mutableStateOf(viewModel4.getBuffer().dateTime)
    }

    var Check by rememberSaveable {
        mutableStateOf(viewModel4.getBuffer().done)
    }

    val state = rememberDatePickerState()

    val context = LocalContext.current

    val openDialog = rememberSaveable { mutableStateOf(false) }

    val itemsPriority = listOf("Work", "Personal")

    var selectedItemPriority by rememberSaveable { mutableStateOf(viewModel4.getBuffer().category) }

    var expandedPriority by rememberSaveable { mutableStateOf(false) }

    val items = listOf("High", "Medium", "Low")

    var selectedItem by rememberSaveable {
        mutableStateOf(
            when (Color(viewModel4.getBuffer().priority)) {
                Color.Red -> "High"
                Color.Yellow -> "Medium"
                Color.Green -> "Low"
                else -> ""
            }
        )
    }
    var expanded by rememberSaveable { mutableStateOf(false) }

    var priorityColor by rememberSaveable {
        mutableStateOf(viewModel4.getBuffer().priority)
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
                        "Update Todo",
                        fontWeight = FontWeight(800),
                        fontStyle = FontStyle.Italic,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }
                    )
                    {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
            {
                OutlinedTextField(
                    shape = RoundedCornerShape(20.dp),
                    value = title, onValueChange = { title = it },
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
                    label = { Text(text = "Enter Description") }, shape = RoundedCornerShape(20.dp),
                )

                Button(
                    onClick = { openDialog.value = true },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(text = "Pick Due Date")
                }
                if (openDialog.value) {
                    DatePickerDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    openDialog.value = false
                                }
                            )
                            {
                                Text("OK")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
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
                    date = viewModel4.getBuffer().dateTime
                }

                Column(
                    modifier = Modifier.padding(10.dp)
                )
                {
                    OutlinedButton(
                        onClick = { expandedPriority = true }


                    )
                    {
                        Text("Category : ${selectedItemPriority}")
                    }
                    DropdownMenu(modifier = Modifier.background(
                        color = Color.White,
                        shape = RectangleShape
                    ),
                        expanded = expandedPriority,
                        onDismissRequest = { expandedPriority = false }
                    )
                    {
                        itemsPriority.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item) },
                                onClick = {
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
                    DropdownMenu(
                        modifier = Modifier.background(color = Color.White, shape = RectangleShape),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    )
                    {
                        items.forEach { item ->
                            DropdownMenuItem(text = { Text(text = item) },
                                onClick = {
                                    selectedItem = item
                                    priorityColor = when (selectedItem) {
                                        items[0] -> Color.Red.toArgb()
                                        items[1] -> Color.Yellow.toArgb()
                                        items[2] -> Color.Green.toArgb()
                                        else -> {
                                            viewModel4.getBuffer().priority
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
                        modifier = Modifier.padding(start = 15.dp, 10.dp),
                        fontWeight = FontWeight(700),

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
            ExtendedFloatingActionButton(modifier = Modifier.padding(
                start = 200.dp,
                top = 20.dp,
                bottom = 20.dp
            ),
                text = { Text(text = "Update") },
                icon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "saveicon") },
                onClick = {
                    if (title.isNotBlank() or note.isNotBlank()) {
                        viewModel4.addTodo(
                            Todo(
                                id = viewModel4.getBuffer().id,
                                title = title,
                                note = note,
                                done = Check,
                                dateTime = date,
                                color = viewModel4.getBuffer().color,
                                category = category,
                                progress = sliderPosition,
                                priority = priorityColor
                            )
                        )
                        Toast.makeText(context, "Updated Todo", Toast.LENGTH_SHORT).show()
                    }
                    viewModel4.set0Buffer()
                    navController.popBackStack()
                }
            )
        }
    }
}