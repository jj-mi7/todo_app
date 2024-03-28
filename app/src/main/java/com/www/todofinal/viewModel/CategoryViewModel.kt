package com.www.todofinal.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.www.todofinal.data.repository.TodoRepo
import com.www.todofinal.data.roomdb.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repo: TodoRepo
) : ViewModel() {

    val todoAll = repo.all
    val high = repo.high
    val medium = repo.medium
    val low = repo.low
    val work = repo.work
    val personal = repo.personal

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.addTodo(todo)
        }
    }

    fun dltTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.dltTodo(todo)
        }
    }
}