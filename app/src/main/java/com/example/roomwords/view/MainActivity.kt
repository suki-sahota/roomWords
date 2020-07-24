package com.example.roomwords.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwords.R
import com.example.roomwords.model.DisplayAdapter
import com.example.roomwords.model.WordEntity
import com.example.roomwords.presenter.Presenter
import kotlinx.android.synthetic.main.layout_fragment_display.*
import kotlinx.coroutines.runBlocking
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity(), IView {

    lateinit var presenter: Presenter
    val fragmentDisplay: FragmentDisplay = FragmentDisplay.newInstance()
    val fragmentCreate: FragmentCreate = FragmentCreate.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindPresenter()
        initFragments()
    }

    override fun bindPresenter() {
        presenter = Presenter() // Late initialization happens here...
        presenter.onBind(this)
    }

    override fun initFragments() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentDisplay)
            .commit()
        displayData(getWords())
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

    override fun getWords(): List<WordEntity> = runBlocking {
        presenter.queryDB()
    }

    override fun isWordEmpty(word: String): Boolean {
        return presenter.isWordEmpty(word)
    }

    override fun saveWord(word: String) = runBlocking {
        presenter.insertWord(word)
    }
}