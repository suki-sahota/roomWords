package com.example.roomwords.presenter

import com.example.roomwords.RoomApplication
import com.example.roomwords.model.WordDao
import com.example.roomwords.model.WordEntity
import com.example.roomwords.model.WordRoomDB
import com.example.roomwords.view.IView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.lang.Runnable

class Presenter {

    var view: IView? = null

    fun onBind(view: IView) {
        this.view = view
    }

    suspend fun queryDB(): List<WordEntity> {
        val dao: WordDao? = RoomApplication.roomContext?.let { WordRoomDB.getInstance(it).dao() }
        return if (dao?.getAllWords() != null) dao.getAllWords() else listOf()
    }

    fun isWordEmpty(word: String): Boolean {
        return word.isEmpty()
    }

    suspend fun insertWord(word: String) {
        val dao: WordDao? = RoomApplication.roomContext?.let { WordRoomDB.getInstance(it).dao() }
        val entry = WordEntity(word = word)
        dao?.insertWord(entry)
        view?.getWords()?.let { view?.displayData(it) } // Updates FragmentDisplay with current info
    }
}