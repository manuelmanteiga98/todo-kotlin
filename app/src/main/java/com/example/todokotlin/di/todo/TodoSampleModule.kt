package com.example.todokotlin.di.todo

import com.example.todokotlin.model.TodoModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TodoSampleModule {
    @Provides
    fun hola():TodoModel{
        return TodoModel("Hola")
    }
}