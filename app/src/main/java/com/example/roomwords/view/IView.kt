package com.example.roomwords.view

import com.example.roomwords.model.WordEntity

interface IView {
    fun bindPresenter()
    fun initFragments()
    fun getWords(): List<WordEntity>

    // F.Display
    fun navigateCreateFragment()
    fun displayData(dataSet: List<WordEntity>)

    // F.Create
    fun isWordEmpty(word: String): Boolean
    fun saveWord(word: String)
}