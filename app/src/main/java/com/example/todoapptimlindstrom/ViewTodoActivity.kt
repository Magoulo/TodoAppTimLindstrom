package com.example.todoapptimlindstrom

import XXX.toDoRepository
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ViewTodoActivity : Activity() {
    private val defaultValueId = 1
    val EXTRA_TITLE = "EXTRA_TITLE"
    val EXTRA_CONTENT = "EXTRA_CONTENT"
    val EXTRA_ID = "EXTRA_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewActivityContent()
    }

    override fun onResume() {
        super.onResume()
        viewActivityContent()
    }

    private fun viewActivityContent() {
        setContentView(R.layout.view_todo_activity)

        val id = intent.getIntExtra("EXTRA_ID", defaultValueId)
        val updatedTodo = toDoRepository.getToDoById(id)
        val title = updatedTodo?.title
        val content = updatedTodo?.content
        val tvTitle = findViewById<TextView>(R.id.textViewTitle)
        val tvContent = findViewById<TextView>(R.id.textViewContent)
        tvTitle.text = title
        tvContent.text = content

        val buttonUpdate = findViewById<Button>(R.id.buttonUpdateTodoActivity)
        val buttonDelete = findViewById<Button>(R.id.buttonDeleteTodoActivity)
        buttonUpdate.setOnClickListener {
            val intent = Intent(
                this,
                UpdateTodoActivity::class.java
            )
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_CONTENT, content)
            intent.putExtra(EXTRA_ID, id)
            startActivity(intent)
        }
        buttonDelete.setOnClickListener {
            val intent = Intent(
                this,
                DeleteTodoActivity::class.java
            )
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_CONTENT, content)
            intent.putExtra(EXTRA_ID, id)
            startActivity(intent)
            finish()
        }
    }
}

