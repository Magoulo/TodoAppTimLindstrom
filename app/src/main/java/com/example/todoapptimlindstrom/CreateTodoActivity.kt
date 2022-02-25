package com.example.todoapptimlindstrom

import XXX.toDoRepository
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CreateTodoActivity : Activity() {
    val MAX_TODO_TITLE_LENGTH = 10
    val MAX_TODO_CONTENT_LENGTH = 50
    val MIN_TODO_TITLE_LENGTH = 1
    val MIN_TODO_CONTENT_LENGTH = 1
    val EXTRA_TITLE = "EXTRA_TITLE"
    val EXTRA_CONTENT = "EXTRA_CONTENT"
    val EXTRA_ID = "EXTRA_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_todo_activity)

        val button = findViewById<Button>(R.id.buttonSaveNewTodo)
        val text = findViewById<EditText>(R.id.editTextTitle)
        val content = findViewById<EditText>(R.id.editTextContent)
        val textError = findViewById<TextView>(R.id.textViewErrorTitle)
        val contentError = findViewById<TextView>(R.id.textViewErrorContent)

        button.setOnClickListener {

            val todoTitle = text.text.toString()
            val todoContent = content.text.toString()
            var newTodoId = 1
            var titleCorrect = false
            var contentCorrect = false

            if(todoTitle.length > MAX_TODO_TITLE_LENGTH || todoTitle.isEmpty()){
                textError.text = "title must be between " + MIN_TODO_TITLE_LENGTH + " - " + MAX_TODO_TITLE_LENGTH + " characters"
            } else {
                textError.text = ""
                titleCorrect = true
            }
            if(todoContent.length > MAX_TODO_CONTENT_LENGTH || todoContent.isEmpty()){
                contentError.text = "Content must contain a description between " + MIN_TODO_CONTENT_LENGTH + " - "+ MAX_TODO_CONTENT_LENGTH +" characters"
            } else {
                contentError.text = ""
                contentCorrect = true
            }
            if(titleCorrect && contentCorrect){
                toDoRepository.addToDo(todoTitle, todoContent)
                val newTodo = toDoRepository.getToDoById(toDoRepository.getAllToDos().size)
                newTodoId = newTodo?.id!!.toInt()

                val intent = Intent(
                    this,
                    ViewTodoActivity::class.java
                )
                intent.putExtra(EXTRA_TITLE, todoTitle)
                intent.putExtra(EXTRA_CONTENT, todoContent)
                intent.putExtra(EXTRA_ID, newTodoId)
                startActivity(intent)
                finish()
            }
        }
    }
}