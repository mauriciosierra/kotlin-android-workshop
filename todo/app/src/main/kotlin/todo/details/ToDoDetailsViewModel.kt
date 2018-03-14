package todo.details

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import todo.model.ToDo
import todo.persistence.ToDoDatabase

class ToDoDetailsViewModel(application: Application) : AndroidViewModel(application) {

    var view: ToDoDetailsView? = null
    private val database: ToDoDatabase = ToDoDatabase.getDatabase(application)
    private var toDo: ToDo? = null

    fun setToDoItemId(id: Int) {
        Thread({
            toDo = database.toDoDao().findById(id)
            toDo?.let {
                updateView(it)
            }
        }).start()
    }

    fun markAsDone() {
        toDo?.let {
            it.completed = true
            Thread({
                database.toDoDao().update(it)
                view?.onMarkedAsDone()
            }).start()
        }
    }

    private fun updateView(toDo: ToDo) {
        view?.apply {
            displayTitle(toDo.title)
            displayDescription(toDo.description)
        }
    }
}