package com.www.todofinal.data.repository

import androidx.lifecycle.LiveData
import com.www.todofinal.data.roomdb.Todo
import com.www.todofinal.data.roomdb.TodoDao
import javax.inject.Inject

class TodoRepo @Inject constructor(private val dao:TodoDao) {

    val all: LiveData<List<Todo>> = dao.showAll()
    val high: LiveData<List<Todo>> = dao.high()
    val medium: LiveData<List<Todo>> = dao.medium()
    val low: LiveData<List<Todo>> = dao.low()
    val work: LiveData<List<Todo>> = dao.work()
    val personal: LiveData<List<Todo>> = dao.personal()

    suspend fun addTodo(todo: Todo) {
        dao.add(todo)
    }

    suspend fun dltTodo(todo: Todo) {
        dao.dlt(todo)
    }

    fun completeTodo(term: String): LiveData<List<Todo>> {
        return dao.complete(term)
    }

    fun searchTodo(term: String): LiveData<List<Todo>> {
        return dao.search(term)
    }

}