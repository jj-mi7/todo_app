package com.www.todofinal.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface TodoDao {

    @Upsert
    suspend fun add(todo: Todo)

    @Delete
    suspend fun dlt(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun showAll():LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE done = 1\n" +
            "AND (title LIKE '%' || :term || '%' OR note LIKE '%' || :term || '%')")
    fun complete(term:String):LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE done = 0\n" +
            "AND (title LIKE '%' || :term || '%' OR note LIKE '%' || :term || '%')")
    fun search(term:String):LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE priority= -256" )
    fun medium():LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE priority= -16711936" )
    fun low():LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE category like '%Work%'" )
    fun work():LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE category like '%Personal%'"  )
    fun personal():LiveData<List<Todo>>

    @Query("SELECT *\n" +
            "FROM Todo\n" +
            "WHERE priority= -65536" )
    fun high():LiveData<List<Todo>>

}