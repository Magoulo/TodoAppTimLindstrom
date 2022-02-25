package com.example.todoapptimlindstrom

import XXX.toDoRepository
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class UpdateTodoActivity : Activity() {
    val MAX_TODO_TITLE_LENGTH = 10
    val MAX_TODO_CONTENT_LENGTH = 50
    val MIN_TODO_TITLE_LENGTH = 1
    val MIN_TODO_CONTENT_LENGTH = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_todo_activity)

        val title = intent.getStringExtra("EXTRA_TITLE")
        val content = intent.getStringExtra("EXTRA_CONTENT")
        val id = intent.getIntExtra("EXTRA_ID", 1)
        val etTitle = findViewById<EditText>(R.id.editTextTitleUpdate)
        val etContent = findViewById<EditText>(R.id.editTextContentUpdate)
        val textError = findViewById<TextView>(R.id.textViewUpdateTitleError)
        val contentError = findViewById<TextView>(R.id.textViewUpdateContentError)

        etTitle.setText(title)
        etContent.setText(content)

        val button = findViewById<Button>(R.id.buttonSaveUpdate)
        button.setOnClickListener {
            var titleCorrect = false
            var contentCorrect = false

            if(etTitle.text.length > MAX_TODO_TITLE_LENGTH || etTitle.text.isEmpty()){
                textError.text = "title must be between " + MIN_TODO_TITLE_LENGTH + " - " + MAX_TODO_TITLE_LENGTH + " characters"
            } else {
                textError.text = ""
                titleCorrect = true
            }
            if(etContent.text.length > MAX_TODO_CONTENT_LENGTH || etContent.text.isEmpty()){
                contentError.text = "Content must contain a description between " + MIN_TODO_CONTENT_LENGTH + " - " + MAX_TODO_CONTENT_LENGTH + " characters"
            } else {
                contentError.text = ""
                contentCorrect = true
            }
            if(titleCorrect && contentCorrect) {
                toDoRepository.updateToDoById(
                    id,
                    etTitle.text.toString(),
                    etContent.text.toString()
                )

            }
            finish()
        }
    }
}