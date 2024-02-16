package com.example.todokotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todokotlin.R
import com.example.todokotlin.databinding.ActivityMainBinding
import com.example.todokotlin.view.adapter.TodoAdapter
import com.example.todokotlin.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    private val mainViewModel: MainViewModel by viewModels()

    private enum class TodoState{COMPLETED, PENDING};

    private var state = TodoState.PENDING


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todosList.layoutManager = LinearLayoutManager(this)
        binding.todosList.adapter = TodoAdapter(ArrayList(), mainViewModel)

        // Add todo listener
        binding.addTodo.setOnClickListener {
            val view:View = layoutInflater.inflate(R.layout.dialog_todo_input, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            builder.setPositiveButton("Add"){ _,_ ->
                val todo = view.findViewById<TextView>(R.id.newTodo).text.toString()
                mainViewModel.addTodo(todo)
            }
            builder.setNegativeButton("Cancel"){_,_-> }
            builder.show()
        }

        // Toolbar click listener
        binding.toolbar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.completed -> {
                    val todos =
                        if(mainViewModel.todos.value!=null) mainViewModel.todos.value!!
                        else ArrayList()
                    binding.todosList.adapter =
                        TodoAdapter(todos.filter { todo -> todo.done }, mainViewModel)
                    state = TodoState.COMPLETED
                    true
                }
                R.id.pending -> {
                    val todos =
                        if(mainViewModel.todos.value!=null) mainViewModel.todos.value!!
                        else ArrayList()
                    binding.todosList.adapter =
                        TodoAdapter(todos.filter { todo -> !todo.done }, mainViewModel)
                    state = TodoState.PENDING
                    true
                }

                else -> super.onOptionsItemSelected(item)
            }
        }

        // Pending todos
        mainViewModel.todos.observe(this){ todos ->
            if(state == TodoState.COMPLETED)
                binding.todosList.adapter = TodoAdapter(todos.filter { todo -> todo.done }, mainViewModel)
            else
                binding.todosList.adapter = TodoAdapter(todos.filter { todo -> !todo.done }, mainViewModel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        return super.onCreateOptionsMenu(menu)
    }

}
