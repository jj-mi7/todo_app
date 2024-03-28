package com.www.todofinal.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.www.todofinal.data.repository.TodoRepo
import com.www.todofinal.data.roomdb.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedViewModel @Inject constructor(
    private val repo: TodoRepo
) : ViewModel() {
    fun completeTodo(term: String): LiveData<List<Todo>> {
        return repo.completeTodo(term)
    }

    fun dltTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.dltTodo(todo)
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.addTodo(todo)
        }
    }
}