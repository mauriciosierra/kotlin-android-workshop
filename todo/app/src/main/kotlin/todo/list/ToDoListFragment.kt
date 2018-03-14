package todo.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tokotlin.todo.R
import todo.model.ToDo

class ToDoListFragment : Fragment() {

    private lateinit var viewModel: ToDoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.todo_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ToDoRecyclerViewAdapter(listOf())

        viewModel = ViewModelProviders.of(this).get(ToDoListViewModel::class.java)
        viewModel.toDoList.observe(this, Observer { toDos -> toDos?.apply {
            updateToDoItems(view.findViewById(R.id.todo_list), toDos) }
        })

        return view
    }

    private fun updateToDoItems(recyclerView: RecyclerView, toDoItems: List<ToDo>) {
        (recyclerView.adapter as ToDoRecyclerViewAdapter).setToDoList(toDoItems)
    }
}
