package todo.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import todo.R
import todo.model.ToDo

class ToDoRecyclerViewAdapter(private var toDoList: List<ToDo>?) : RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoViewHolder>() {

    fun setToDoList(toDoList: List<ToDo>) {
        this.toDoList = toDoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bindToDoItem(toDoList?.get(position))
    }

    override fun getItemCount(): Int {
        return toDoList?.size ?: 0
    }

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindToDoItem(toDo: ToDo?) {
            itemView.findViewById<TextView>(R.id.title).text = toDo?.title ?: ""
            itemView.findViewById<TextView>(R.id.description).text = toDo?.description ?: ""
        }
    }
}
