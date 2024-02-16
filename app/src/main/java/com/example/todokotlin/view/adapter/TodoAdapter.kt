package com.example.todokotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.todokotlin.R
import com.example.todokotlin.model.TodoModel
import com.example.todokotlin.viewmodel.MainViewModel

class TodoAdapter(private val todos: List<TodoModel>, private val mainViewModel: MainViewModel): RecyclerView.Adapter<TodoViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.todo_item, viewGroup, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.render(todos[position])
        holder.itemView.findViewById<CheckBox>(R.id.checked).setOnClickListener {
            mainViewModel.checkTodo(todos[position].id)
        }
        holder.itemView.findViewById<ImageView>(R.id.delete).setOnClickListener {
            mainViewModel.deleteTodo(todos[position].id)
        }
        holder.itemView.findViewById<ImageView>(R.id.backarrow).setOnClickListener {
            mainViewModel.uncheckTodo(todos[position].id)
        }
    }

    override fun getItemCount() = todos.size
}