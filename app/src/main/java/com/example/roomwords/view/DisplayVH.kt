package com.example.roomwords.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwords.model.WordEntity
import kotlinx.android.synthetic.main.display_item_layout.view.*

class DisplayVH(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tvValue = itemView.tv_item

    fun onBind(entry: WordEntity) {
        tvValue.text = entry.word
    }
}