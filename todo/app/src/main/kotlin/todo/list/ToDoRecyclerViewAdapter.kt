package todo.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import todo.model.ToDo
import tokotlin.todo.R
import kotlinx.android.synthetic.main.todo_list_item.view.*

class ToDoRecyclerViewAdapter(private var toDoList: List<ToDo>?) : RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoViewHolder>() {

    var listener : ((ToDo) -> Unit)? = null

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

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindToDoItem(toDo: ToDo?) {
            itemView.title.text = toDo?.title ?: ""
            itemView.description.text = toDo?.description ?: ""
            toDo?.let {
                itemView.setOnClickListener({ _ -> listener?.invoke(it) })
            }
        }
    }
}
