package tokotlin.todo.list

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
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import tokotlin.todo.R
import tokotlin.todo.details.ToDoDetailsActivity
import tokotlin.todo.model.ToDo

class ToDoListFragment : Fragment() {

    private lateinit var viewModel: ToDoListViewModel
    private lateinit var adapter: ToDoRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        adapter = ToDoRecyclerViewAdapter(listOf())
        adapter.listener = ::onItemSelected
        view.todo_list.layoutManager = LinearLayoutManager(context)
        view.todo_list.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(ToDoListViewModel::class.java)
        viewModel.toDoList.observe(this, Observer { toDos ->
            toDos?.apply {
                updateToDoItems(todo_list, toDos)
            }
        })

        return view
    }

    private fun updateToDoItems(recyclerView: RecyclerView, toDoItems: List<ToDo>) {
        (recyclerView.adapter as ToDoRecyclerViewAdapter).setToDoList(toDoItems)
    }

    inline fun onItemSelected(toDo: ToDo) {
        val intent = Intent(context, ToDoDetailsActivity::class.java)
        intent.putExtra(ToDoDetailsActivity.ARG_ITEM_ID, toDo.itemId)
        startActivity(intent)
    }
}
