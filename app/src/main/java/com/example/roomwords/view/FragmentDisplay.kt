package com.example.roomwords.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomwords.R
import com.example.roomwords.model.DisplayAdapter
import com.example.roomwords.model.WordEntity
import kotlinx.android.synthetic.main.layout_fragment_display.*
import kotlinx.android.synthetic.main.layout_fragment_display.view.*

class FragmentDisplay: Fragment() {

    var listener: IView? = null
    private val adapter: DisplayAdapter by lazy { DisplayAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Prevents failure when FragmentDisplay is implemented in a different activity than where IView is inherited
        if (context is MainActivity) listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.layout_fragment_display, container, false)
        view.foab.setOnClickListener {
            listener?.navigateCreateFragment()
        }
        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = adapter // Calls constructor in lazy block
        return view
    }

    fun displayData(dataSet: List<WordEntity>) {
        adapter.dataSet = dataSet
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): FragmentDisplay {
            return FragmentDisplay()
        }
    }
}