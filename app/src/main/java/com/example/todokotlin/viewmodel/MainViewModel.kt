package com.example.todokotlin.viewmodel

import  androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todokotlin.model.TodoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todo: TodoModel
): ViewModel() {

    val todos = MutableLiveData<ArrayList<TodoModel>>()
    val completedTodos = MutableLiveData<ArrayList<TodoModel>>()

    fun addTodo(newTodo: String){
        val todo = TodoModel(newTodo)
        val todosAux: ArrayList<TodoModel> = if(todos.value==null){
            ArrayList()
        } else{
            todos.value!!
        }
        if(todosAux.size>0)
            todo.id = todosAux[todosAux.size-1].id+1
        todosAux.add(todo)
        todos.postValue(todosAux)
    }

    fun deleteTodo(id:Int){
        val todosAux: ArrayList<TodoModel> = if(todos.value==null){
            return
        } else{
            todos.value!!
        }
        todos.postValue(todosAux.filter { item -> item.id !=id } as ArrayList<TodoModel>?)
    }

    fun checkTodo(id:Int){
        val todosAux: ArrayList<TodoModel> = if(todos.value==null){
            return
        } else{
            todos.value!!
        }
        todosAux.find { todo -> todo.id == id }?.done = true
        todos.postValue(todosAux)
    }

    fun uncheckTodo(id:Int){
        val todosAux: ArrayList<TodoModel> = if(todos.value==null){
            return
        } else{
            todos.value!!
        }
        todosAux.find { todo -> todo.id == id }?.done = false
        todos.postValue(todosAux)
    }

}