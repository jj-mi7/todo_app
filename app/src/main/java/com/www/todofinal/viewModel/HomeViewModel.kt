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
class HomeViewModel @Inject constructor(
    private val repo: TodoRepo
) : ViewModel() {
    val todoAll = repo.all

    fun searchTodo(term: String): LiveData<List<Todo>> {
        return repo.searchTodo(term)
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTodo(todo)
        }
    }

    fun dltTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.dltTodo(todo)
        }
    }

}