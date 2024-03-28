package com.www.todofinal.di

import android.app.Application
import androidx.room.Room
import com.www.todofinal.data.repository.TodoRepo
import com.www.todofinal.data.roomdb.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun giveTodoDb(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun makeRepo(db: TodoDatabase): TodoRepo {
        return TodoRepo(db.todoDao())
    }
}