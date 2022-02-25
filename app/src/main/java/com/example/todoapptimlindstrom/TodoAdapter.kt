package com.example.todoapptimlindstrom

import XXX.ToDo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val toDos : MutableList<ToDo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val todoTitle : TextView = itemView.findViewById(R.id.tvTodoTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo,parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curItem = toDos[position]
        holder.todoTitle.text = curItem.title
    }

    override fun getItemCount(): Int {
        return toDos.size
    }
}


