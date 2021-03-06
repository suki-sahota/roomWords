package com.example.roomwords.presenter

import com.example.roomwords.RoomApplication
import com.example.roomwords.model.WordDao
import com.example.roomwords.model.WordEntity
import com.example.roomwords.model.WordRoomDB
import com.example.roomwords.view.IView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Presenter {

    private lateinit var view: IView

    fun onBind(view: IView) {
        this.view = view // Late initialization happens here...
    }

    fun queryDB(): List<WordEntity> = runBlocking(Dispatchers.IO) {
        val dao: WordDao? = RoomApplication.roomContext?.let { WordRoomDB.getInstance(it).dao() }
        dao?.getAllWords() ?: listOf()
    }

    fun isWordEmpty(word: String): Boolean {
        return word.isEmpty()
    }

    fun insertWord(word: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val dao: WordDao? =
                RoomApplication.roomContext?.let { WordRoomDB.getInstance(it).dao() }
            val entry = WordEntity(word = word)
            dao?.insertWord(entry)
        }
        view.displayData(queryDB()) // Updates FragmentDisplay with current info in the Main Thread
//        view?.getWords()?.let { view?.displayData(it) } // Updates FragmentDisplay with current info
    }
}