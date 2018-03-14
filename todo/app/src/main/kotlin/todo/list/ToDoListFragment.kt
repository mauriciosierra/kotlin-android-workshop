package todo.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.view.*
import todo.details.ToDoDetailsActivity
import todo.model.ToDo
import tokotlin.todo.R

class ToDoListFragment : Fragment() {

    private lateinit var viewModel: ToDoListViewModel

    private lateinit var todoAdapter: ToDoRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        fun showToDoDetails(toDo: ToDo) {
            val detailsIntent = Intent(context, ToDoDetailsActivity::class.java)
            detailsIntent.putExtra(ToDoDetailsActivity.ARG_ITEM_ID, toDo.itemId)
            startActivity(detailsIntent)
        }

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        todoAdapter = ToDoRecyclerViewAdapter(listOf())

        todoAdapter.listener = { toDo ->
            showToDoDetails(toDo)
        }

        with (view.todo_list) {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }

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
