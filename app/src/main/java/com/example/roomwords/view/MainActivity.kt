package com.example.roomwords.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomwords.R
import com.example.roomwords.model.WordEntity
import com.example.roomwords.presenter.Presenter

class MainActivity : AppCompatActivity(), IView {

    private val presenter: Presenter by lazy { Presenter() }
    private val fragmentDisplay: FragmentDisplay = FragmentDisplay.newInstance()
    private val fragmentCreate: FragmentCreate = FragmentCreate.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindPresenter()
        initFragments()
    }

    override fun bindPresenter() {
        presenter.onBind(this) // Calls constructor in lazy block...
    }

    override fun initFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentDisplay)
            .commit()
        displayData(getWords())
    }

    override fun getWords(): List<WordEntity> {
        return presenter.queryDB()
    }

    override fun displayData(dataSet: List<WordEntity>) {
        fragmentDisplay.displayData(dataSet)
    }

    override fun navigateCreateFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentCreate)
            .addToBackStack("create")
            .commit()
    }

    override fun isWordEmpty(word: String): Boolean {
        return presenter.isWordEmpty(word)
    }

    override fun saveWord(word: String) {
        presenter.insertWord(word)
    }
}