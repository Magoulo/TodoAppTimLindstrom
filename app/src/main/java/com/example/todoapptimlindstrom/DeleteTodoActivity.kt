package com.example.todoapptimlindstrom

import XXX.toDoRepository
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class DeleteTodoActivity: Activity() {
    private val defaultValueId = 1
    val EXTRA_ID = "EXTRA_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_todo_activity)

        val buttonDeleteYes = findViewById<Button>(R.id.buttonDeleteYes)
        val buttonDeleteNo = findViewById<Button>(R.id.buttonDeleteNo)
        val id = intent.getIntExtra("EXTRA_ID",defaultValueId)

        buttonDeleteYes.setOnClickListener{
            toDoRepository.deleteToDoById(id)
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
            finish()
        }
        buttonDeleteNo.setOnClickListener{
            val intent = Intent(this,
                ViewTodoActivity::class.java)
            intent.putExtra(EXTRA_ID,id)
            startActivity(intent)
            finish()
        }
    }
}