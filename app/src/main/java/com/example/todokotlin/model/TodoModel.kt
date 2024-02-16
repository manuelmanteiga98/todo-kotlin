package com.example.todokotlin.model

data class TodoModel(val content:String, var id:Int=0, var done:Boolean=false);