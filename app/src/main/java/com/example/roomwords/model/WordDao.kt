package com.example.roomwords.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao // Data Access Object
interface WordDao { // An advantage of Room is that you get access to all the joins from SQL

    @Query("SELECT * FROM word_table") // Read
    suspend fun getAllWords(): List<WordEntity> // Ready for multithreading with RxJava or Coroutines

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Create
    suspend fun insertWord(singleWord: WordEntity)

    @Query("DELETE FROM word_table") // Destroy
    suspend fun deleteAll()
}