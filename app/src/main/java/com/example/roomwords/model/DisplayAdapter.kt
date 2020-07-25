package com.example.roomwords.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwords.R
import com.example.roomwords.view.DisplayVH

class DisplayAdapter(): RecyclerView.Adapter<DisplayVH>() {

    var dataSet: List<WordEntity> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DisplayVH(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.display_item_layout,
                    parent,
                    false
                )
        )

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: DisplayVH, position: Int) {
        dataSet.let {
            holder.onBind(it[position])
        }
    }
}