package todo.details

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_to_do_details.*
import tokotlin.todo.R

class ToDoDetailsActivity : AppCompatActivity(), ToDoDetailsView {

    companion object {
        const val ARG_ITEM_ID = "todo_item_id"
    }

    private lateinit var viewModel: ToDoDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_details)

        viewModel = ViewModelProviders.of(this).get(ToDoDetailsViewModel::class.java)
        viewModel.view = this

        setupView()
    }

    private fun setupView() {
        mark_as_done.setOnClickListener({ _ -> viewModel.markAsDone() })
        val toDoId = intent.extras.getInt(ARG_ITEM_ID)
        viewModel.setToDoItemId(toDoId)
    }

    override fun displayTitle(title: String) = runOnUiThread {
        todo_title.text = title
    }

    override fun displayDescription(description: String) = runOnUiThread {
        todo_description.text = description
    }

    override fun onMarkedAsDone() = runOnUiThread {
        finish()
    }
}
