package com.example.todoapptimlindstrom

import XXX.toDoRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    val EXTRA_TITLE = "EXTRA_TITLE"
    val EXTRA_CONTENT = "EXTRA_CONTENT"
    val EXTRA_ID = "EXTRA_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainContent()
    }

    override fun onResume() {
        super.onResume()
        mainContent()

    }

    private fun mainContent() {
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.rvTodoItems)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = TodoAdapter(toDoRepository.getAllToDos())
        rv.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                rv,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {

                        val todos = toDoRepository.getAllToDos()
                        val todo = todos[position]
                        val todoTitle = todo?.title
                        val todoContent = todo?.content
                        val todoId = todo?.id
                        val intent = Intent(
                            view.context,
                            ViewTodoActivity::class.java
                        )
                        intent.putExtra(EXTRA_TITLE, todoTitle)
                        intent.putExtra(EXTRA_CONTENT, todoContent)
                        intent.putExtra(EXTRA_ID, todoId)
                        startActivity(intent)
                    }
                })
        )
        val button = findViewById<FloatingActionButton>(R.id.buttonCreateTodo)
        button.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CreateTodoActivity::class.java
                )
            )
        }
    }
}

