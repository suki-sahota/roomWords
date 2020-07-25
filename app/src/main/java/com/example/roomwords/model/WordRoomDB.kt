package com.example.roomwords.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomwords.R

@Database(entities = [WordEntity::class], version = 1, exportSchema = false)
abstract class WordRoomDB: RoomDatabase() { // The actual Room DB is created here...

    abstract fun dao(): WordDao

    companion object {
        @Volatile
        var INSTANCE: WordRoomDB? = null

        fun getInstance(context: Context): WordRoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            val instance: WordRoomDB = Room.databaseBuilder(
                context, WordRoomDB::class.java,
                context.getString(R.string.db_name)
            )
//                .createFromAsset("database/testData.db")
                .build()
            INSTANCE = instance
            return instance
        }
    }
}