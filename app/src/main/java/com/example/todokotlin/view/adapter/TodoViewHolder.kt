package com.example.todokotlin.view.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todokotlin.R
import com.example.todokotlin.model.TodoModel

class TodoViewHolder(view: View): ViewHolder(view) {

    private var id: Int = 0
    private var content: TextView = view.findViewById(R.id.content)

    fun render(todoModel: TodoModel){
        content.text = todoModel.content
        id = todoModel.id
        if(todoModel.done) {
            itemView.findViewById<CheckBox>(R.id.checked).visibility = View.GONE
            itemView.findViewById<ImageView>(R.id.backarrow).visibility = View.VISIBLE
            itemView.findViewById<ImageView>(R.id.delete).visibility = View.VISIBLE
        }
    }

}