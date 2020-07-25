package com.example.roomwords.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.roomwords.R
import kotlinx.android.synthetic.main.layout_fragment_create.view.*

class FragmentCreate: Fragment() {

    private lateinit var listener: IView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Ensures fragment is implemented in an activity that inherits IView
        if (context is MainActivity) listener = context // Late initialization happens here...
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.layout_fragment_create, container, false)

        view.btn_save_word.setOnClickListener {
            if (listener.isWordEmpty(view.til_word.editText?.text.toString())) {
                Toast.makeText(activity, "Please type something!", Toast.LENGTH_LONG).show()
            } else {
                listener.saveWord(view.til_word.editText?.text.toString())
                view.til_word.editText?.text?.clear()
                activity!!.supportFragmentManager.popBackStackImmediate()
            }
        }
        return view
    }

    companion object {
        fun newInstance(): FragmentCreate {
            return FragmentCreate()
        }
    }
}