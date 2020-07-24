package com.example.roomwords.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class WordEntity( // Used to model a SQLite DB inside the room database (like a Schema)
    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
    @ColumnInfo(name = "word_column")
    val word: String
)