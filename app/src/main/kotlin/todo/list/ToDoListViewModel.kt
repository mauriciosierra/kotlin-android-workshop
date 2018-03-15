package todo.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import todo.model.ToDo
import todo.persistence.ToDoDatabase

class ToDoListViewModel(application: Application) : AndroidViewModel(application) {

    val toDoList: LiveData<List<ToDo>>

    private val database: ToDoDatabase = ToDoDatabase.getDatabase(application)

    init {
        toDoList = database.toDoDao().all
    }
}
